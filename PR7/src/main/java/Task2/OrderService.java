package Task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OrderService {
    private static  final Logger log = LoggerFactory.getLogger(OrderService.class);


    public static void checkout(Order order) {
        try{
            ProcessOrder(order);
            log.info("Order has been successfully processed");
        }
        catch(IllegalArgumentException e){
            log.error("Order hasn't been processed", e);
            throw new OrderProcessingException("totalCents value must be lower than 1000", e, order.getId(), order.getEmail());
        }
        catch(IOException e){
            log.warn("Invalid card", e);
        }
    }

    private static void ProcessOrder(Order order) throws IOException {
        if(order.totalCents > 1000){
            throw new IllegalArgumentException();
        }
        if(!cardChecker(order.cardNumber)){
            throw new IOException();
        }
    }

    private static boolean cardChecker(String cardNumber){
        return cardNumber.matches("[0-9]{12}");
    }
}
