package service_factory;

public class CardPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Payed with card: " + amount);
    }
}
