package Task3;

import java.io.IOException;

public class ReceiptWriter implements AutoCloseable{
    public void writeReceipt(String data) throws IOException {
        System.out.println("Запис даних у чек: " + data);
    }

    @Override
    public void close() {
        System.out.println("Метод close() викликано автоматично. Ресурс закрито.");
    }
}
