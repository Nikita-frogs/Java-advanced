package Task2;

public class OrderProcessingException extends AppException {
    public OrderProcessingException(String message, Throwable cause, String index, String email) {
        super(message, cause);
        System.out.println("ID:" + index);
        System.out.println("Email:" + email);
    }
}
