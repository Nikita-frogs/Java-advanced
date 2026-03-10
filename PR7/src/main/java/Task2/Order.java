package Task2;

public class Order {
    String id;
    String email;
    int totalCents;
    String cardNumber;

    public Order(String id, String email, int totalCents, String cardNumber){
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
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
}
