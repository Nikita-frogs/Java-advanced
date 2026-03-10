package Task2;

public class OrderMain {
    public static void main(String[] args) {
        Order order = new Order("i1982983", "njvbfh@jnhjn", 1000, "4441110099998888");
        OrderService.checkout(order);
    }
}
