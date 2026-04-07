package payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentLoader {

    private static final Logger LOG = Logger.getLogger(PaymentLoader.class.getName());

    /** Expected number of columns in every data row. */
    private static final int EXPECTED_COLUMNS = 4;

    // -----------------------------------------------------------------------
    // Public API
    // -----------------------------------------------------------------------

    /**
     * Convenience method – returns only the valid payments list.
     * Internally delegates to {@link #loadWithStats(Path)}.
     *
     * @param csv path to the CSV file
     * @return immutable list of valid {@link Payment} objects
     * @throws IOException if the file cannot be opened or read
     */
    public List<Payment> load(Path csv) throws IOException {
        return loadWithStats(csv).payments();
    }

    /**
     * Parses the CSV file and returns both the valid payments and the count of
     * invalid (skipped) data rows.
     *
     * @param csv path to the CSV file
     * @return {@link LoadResult} containing payments and invalidLines count
     * @throws IOException if the file cannot be opened or read
     */
    public LoadResult loadWithStats(Path csv) throws IOException {
        List<Payment> payments = new ArrayList<>();
        int invalidLines = 0;

        // try-with-resources ensures the reader is always closed
        try (BufferedReader reader = Files.newBufferedReader(csv, StandardCharsets.UTF_8)) {

            // Skip header line
            String header = reader.readLine();
            if (header == null) {
                // Empty file – nothing to read
                return new LoadResult(payments, invalidLines);
            }

            String line;
            while ((line = reader.readLine()) != null) {

                // Blank lines are silently ignored (not counted as invalid)
                if (line.isBlank()) {
                    continue;
                }

                Payment payment = parseLine(line);
                if (payment == null) {
                    invalidLines++;
                } else {
                    payments.add(payment);
                }
            }
        }

        return new LoadResult(payments, invalidLines);
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    /**
     * Tries to parse a single CSV data line into a {@link Payment}.
     *
     * @param line raw CSV line (never blank)
     * @return parsed {@link Payment}, or {@code null} if the line is invalid
     */
    private Payment parseLine(String line) {
        String[] parts = line.split(",", -1); // -1 keeps trailing empty tokens

        if (parts.length != EXPECTED_COLUMNS) {
            LOG.log(Level.WARNING, "Skipping line with {0} column(s) (expected {1}): {2}",
                    new Object[]{parts.length, EXPECTED_COLUMNS, line});
            return null;
        }

        try {
            long id             = Long.parseLong(parts[0].trim());
            String email        = parts[1].trim();
            PaymentStatus status = PaymentStatus.fromString(parts[2]);
            int amountCents     = Integer.parseInt(parts[3].trim());

            return new Payment(id, email, status, amountCents);

        } catch (NumberFormatException e) {
            LOG.log(Level.WARNING, "Skipping line with non-numeric id/amount: {0}", line);
        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "Skipping invalid line [{0}]: {1}", new Object[]{e.getMessage(), line});
        }

        return null;
    }
}
