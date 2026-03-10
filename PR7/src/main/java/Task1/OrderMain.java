package Task1;

public class OrderMain {
    public static void main(String[] args) {
        try{
            Order order = new Order(null, "null", 0);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        try{
            Order order = new Order("p129238928", "eufhrg", 15);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        try{
            Order order = new Order("p129238928", "euf@com", -15);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        try{
            Order order = new Order("p129238928", "euf@com", 15);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
