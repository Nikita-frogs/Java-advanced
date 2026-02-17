public class CardPayment implements PaymentMethod{
    @Override
    public String name() {
        return "Card Payment";
    }

    @Override
    public void pay(int Amount) {
        System.out.println("Paying " + Amount + " with " + name());
    }
}
