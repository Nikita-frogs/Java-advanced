package Task1;

public class Order {
    String id;
    String email;
    int totalCents;

    public Order(String id, String email, int totalCents){
        if(totalCents < 0){
            throw new IllegalArgumentException("Total Cents cannot be negative");
        }
        this.totalCents = totalCents;
        if(!email.contains("@")){
            throw new IllegalArgumentException("Email must contain '@'");
        }
        this.email = email;
        if(id.isEmpty()){
            throw new IllegalArgumentException("ID cannot be empty");
        }
        this.id = id;
    }
}
