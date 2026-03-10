public class TightCheckoutProcessor {
    private PaymentGateway gateway = new StripePaymentGateway();
    private NotificationService notifier = new EmailNotificationService();

    public PaymentResult checkout(Order order, PaymentDetails details, Email email) {
        Money total = order.total();
        PaymentResult result = gateway.charge(total, details);
        if (result.isSuccess()) {
            notifier.sendReceipt(email, total);
        }
        return result;
    }

    public static interface PaymentGateway {
        PaymentResult charge(Money amount, PaymentDetails details);
    }
}
