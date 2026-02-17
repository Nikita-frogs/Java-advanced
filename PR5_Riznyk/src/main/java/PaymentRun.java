public class PaymentRun {
    public static void main(String[] args) {
        PaymentMethod cardPayment = new CardPayment();
        PaymentMethod paypalPayment = new PayPalPayment();

        cardPayment.pay(100);
        paypalPayment.pay(200);

        cardPayment.payWithFee(100, 10);
        paypalPayment.payWithFee(200, 20);
    }
}
