package service_factory;

public class CashPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Payed with cash: " + amount);
    }
}
