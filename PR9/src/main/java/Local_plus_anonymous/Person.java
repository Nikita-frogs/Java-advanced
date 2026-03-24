package Local_plus_anonymous;

public class Person {
    String firstName = "Nikita";
    String lastName = "Riznyk";

    public String buildTicketId(){
        class IdBuilder{
            private String id;
            IdBuilder(){
                id = firstName.toCharArray()[0]+"-"+lastName.toCharArray()[0]+"-"+java.util.UUID.randomUUID().toString();
            }
            public String showId(){
                return id;
            }
        }
        IdBuilder idBuilder = new IdBuilder();
        return idBuilder.showId();
    }

    public Runnable runOnce(){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Using runnable "+buildTicketId());
            }
        };
    }
}
