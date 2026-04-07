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


public class PaymentReportWriter {

    public void writeReport(Path out, List<Payment> payments, int invalidLines) throws IOException {

        // ── 1. Compute statistics ────────────────────────────────────────────

        long paidTotalCents = payments.stream()
                .filter(p -> p.status() == PaymentStatus.PAID)
                .mapToLong(Payment::amountCents)
                .sum();

        Map<PaymentStatus, Long> countByStatus = payments.stream()
                .collect(Collectors.groupingBy(Payment::status, Collectors.counting()));

        long newCount    = countByStatus.getOrDefault(PaymentStatus.NEW,    0L);
        long paidCount   = countByStatus.getOrDefault(PaymentStatus.PAID,   0L);
        long failedCount = countByStatus.getOrDefault(PaymentStatus.FAILED, 0L);

        // ── 2. Resolve the target directory ─────────────────────────────────

        // out.getParent() is null when the path has no parent component (e.g. "2025.txt")
        Path targetDir = out.toAbsolutePath().getParent();
        if (targetDir != null) {
            Files.createDirectories(targetDir);
        } else {
            targetDir = Path.of(".");
        }

        // ── 3. Write to a sibling temp file ─────────────────────────────────
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

    private static void tryDelete(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException ignored) {
            // Nothing we can do at this point
        }
    }
}