public class PayPalPayment implements PaymentMethod{
    @Override
    public String name() {
        return "PayPal Payment";
    }

    @Override
    public void pay(int Amount) {
        System.out.println("Paying " + Amount + " with " + name());
    }
}
