public class NotifyRun {
    public static void main(String[] args) {
        MultiNotifier notifier = new MultiNotifier();
        notifier.send("Hello, this is a multi-notification message!");
    }
}
