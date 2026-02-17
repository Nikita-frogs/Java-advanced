public interface PaymentMethod {
    String name();
    void pay(int Amount);
    default  void payWithFee(int Amount, int fee) {
        System.out.println("Paying " + (Amount + fee) + " with " + name());
    }
}
