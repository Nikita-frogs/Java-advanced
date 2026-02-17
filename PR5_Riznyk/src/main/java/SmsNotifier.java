public interface SmsNotifier {
    default void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
