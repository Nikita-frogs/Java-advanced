package payment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Writes a summary report of payments to a text file using an atomic write strategy:
 * <ol>
 *   <li>Writes all content to a temp file in the <em>same directory</em> as the target.</li>
 *   <li>Atomically moves the temp file over the target with {@code REPLACE_EXISTING}.</li>
 *   <li>Falls back to a non-atomic move if the OS/FS does not support {@code ATOMIC_MOVE}.</li>
 * </ol>
 *
 * <p>Report format:
 * <pre>
 *   invalidLines=7
 *   paidTotalCents=5798
 *   NEW=1, PAID=2, FAILED=2
 * </pre>
 */
public class PaymentReportWriter {

    /**
     * Generates and atomically writes the report.
     *
     * @param out          target report file path
     * @param payments     list of valid payments (used for statistics)
     * @param invalidLines number of invalid CSV lines (from {@link LoadResult})
     * @throws IOException if writing or moving the file fails
     */
    public void writeReport(Path out, List<Payment> payments, int invalidLines) throws IOException {

        // ── 1. Compute statistics ────────────────────────────────────────────

        long paidTotalCents = payments.stream()
                .filter(p -> p.status() == PaymentStatus.PAID)
                .mapToLong(Payment::amountCents)
                .sum();

        // Count per status, defaulting missing statuses to 0
        Map<PaymentStatus, Long> countByStatus = payments.stream()
                .collect(Collectors.groupingBy(Payment::status, Collectors.counting()));

        long newCount    = countByStatus.getOrDefault(PaymentStatus.NEW,    0L);
        long paidCount   = countByStatus.getOrDefault(PaymentStatus.PAID,   0L);
        long failedCount = countByStatus.getOrDefault(PaymentStatus.FAILED, 0L);

        // ── 2. Resolve the target directory ─────────────────────────────────

        // out.getParent() is null when the path has no parent component (e.g. "report.txt")
        Path targetDir = out.toAbsolutePath().getParent();
        if (targetDir != null) {
            Files.createDirectories(targetDir);
        } else {
            targetDir = Path.of(".");
        }

        // ── 3. Write to a sibling temp file ─────────────────────────────────
        //
        // Using the same directory is crucial for ATOMIC_MOVE:
        // an atomic rename is only possible within a single filesystem mount point.

        Path tmp = Files.createTempFile(targetDir, ".report-", ".tmp");

        try {
            try (BufferedWriter writer = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)) {
                writer.write("invalidLines=" + invalidLines);
                writer.newLine();

                writer.write("paidTotalCents=" + paidTotalCents);
                writer.newLine();

                writer.write("NEW=" + newCount + ", PAID=" + paidCount + ", FAILED=" + failedCount);
                writer.newLine();
            }

            // ── 4. Atomic move (with REPLACE_EXISTING fallback) ─────────────

            atomicMove(tmp, out.toAbsolutePath());

        } catch (IOException writeEx) {
            // Clean up temp file if anything went wrong before/during the move
            tryDelete(tmp);
            throw writeEx;
        }
    }

    // ── Private helpers ──────────────────────────────────────────────────────

    /**
     * Tries an {@code ATOMIC_MOVE + REPLACE_EXISTING} first.
     * Falls back to a plain {@code REPLACE_EXISTING} move when the OS or filesystem
     * does not support atomic renames (e.g. cross-device moves, some network FS).
     */
    private static void atomicMove(Path source, Path target) throws IOException {
        try {
            Files.move(source, target,
                    StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (AtomicMoveNotSupportedException ignored) {
            // Non-atomic fallback — still uses REPLACE_EXISTING to avoid partial files
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /** Best-effort temp file deletion — swallows exceptions to avoid masking the real error. */
    private static void tryDelete(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException ignored) {
            // Nothing we can do at this point
        }
    }
}