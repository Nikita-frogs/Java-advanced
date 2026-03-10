public class LoggingCheckoutServiceInherited extends CheckoutService {
    @Override
    public String checkout(String orderId) {
        System.out.println("Start checkout");
        String result = super.checkout(orderId);
        System.out.println("End checkout");
        return result;
    }
}
