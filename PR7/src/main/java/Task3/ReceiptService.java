package Task3;

public class ReceiptService {

    public static void generate(String receiptData) {
        try (ReceiptWriter writer = new ReceiptWriter()) {
            writer.writeReceipt(receiptData);
        } catch (Exception e) {
            throw new ReceiptGenerationException("Помилка при створенні чека", e);
        }
    }
}
