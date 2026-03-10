package Task2;

public class PaymentGatewayException extends Exception {
    public PaymentGatewayException(String message,  Throwable cause, String cardNumber) {
        super(message, cause);
        System.err.println(message);
        System.err.println(cardNumber);
    }
}
