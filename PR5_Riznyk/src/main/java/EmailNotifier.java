public interface EmailNotifier {
    default void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}
