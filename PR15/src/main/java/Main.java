import reflection_info.Book;
import reflection_info.ClassInfoPrinter;
import service_factory.*;

public class Main {
    public static void main(String[] args) {
        Book b = new Book("IT","Steven King",225);
        Class<?> clazz = b.getClass();
        ClassInfoPrinter.print(clazz);

        System.out.println("-----Service factory-----");
        String cardClassName = "service_factory.CardPaymentService";
        String cashClassName = "service_factory.CashPaymentService";

        PaymentService cardService = (PaymentService) ServiceFactory.create(cardClassName);
        cardService.pay(1500.50);

        PaymentService cashService = (PaymentService) ServiceFactory.create(cashClassName);
        cashService.pay(300.00);
    }
}
