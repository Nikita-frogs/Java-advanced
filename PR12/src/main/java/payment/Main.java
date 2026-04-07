package payment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static payment.StatusFile.readStatus;

public class Main {
    public static void main(String[] args) throws IOException {
        // Determine CSV path: CLI arg or default sample
        Path csv = args.length > 0
                ? Path.of(args[0])
                : Path.of("src/main/java/payment/payments.csv");

        System.out.println("=== Payment Loader ===");
        System.out.println("Reading: " + csv.toAbsolutePath());
        System.out.println();

        // ── Load ─────────────────────────────────────────────────────────────
        PaymentLoader loader = new PaymentLoader();
        LoadResult result = loader.loadWithStats(csv);  // IOException іде нагору

        System.out.printf("✅ Valid payments  : %d%n", result.payments().size());
        System.out.printf("❌ Invalid lines   : %d%n", result.invalidLines());
        System.out.println();

        if (!result.payments().isEmpty()) {
            System.out.println("--- Valid payments ---");
            result.payments().forEach(p ->
                    System.out.printf("  [%d] %-28s %-6s  %7.2f UAH%n",
                            p.id(), p.email(), p.status(), p.amountCents() / 100.0)
            );
        }

        // ── Write report ─────────────────────────────────────────────────────
        Path reportPath = csv.toAbsolutePath().getParent().resolve("2025.txt");
        new PaymentReportWriter().writeReport(reportPath, result.payments(), result.invalidLines());

        System.out.println();
        System.out.println("--- Report written to: " + reportPath + " ---");
        System.out.println(Files.readString(reportPath));

        Path inbox = Path.of("src/practical-data/inbox");
        Path archive = Path.of("src/practical-data/archive");
        InboxArchiver.archiveTmpFiles(inbox, archive);

//        Path base = Path.of("src/practical-data");
//        Path userInput = Path.of("../secret.txt");
//        PathSafety.safeResolve(base, userInput);

        File file = new File("status.bin");
        int nBytes = 10;

        try {
            StatusFile.createFileWithZeros(file, nBytes);
            System.out.println("Файл створено. Розмір: " + file.length() + " байтів.");

            int indexToUpdate = 4;
            byte newStatus = 7;

            StatusFile.updateStatus(file, indexToUpdate, newStatus);
            System.out.println("Статус за індексом " + indexToUpdate + " успішно оновлено на " + newStatus);

            byte readStatus = readStatus(file, indexToUpdate);
            System.out.println("Прочитаний статус з індексу " + indexToUpdate + ": " + readStatus);

        } catch (IOException e) {
            System.err.println("Помилка вводу/виводу: " + e.getMessage());
        }
    }
}