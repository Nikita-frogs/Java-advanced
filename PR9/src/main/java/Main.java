import Local_plus_anonymous.Person;
import Refactoring_anonymus_lambda.Ticket;
import Refactoring_anonymus_lambda.TicketSorter;
import Static_nested_vs_inner.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Car.engineSpec());

        Library library = new Library();
        System.out.println(library.book().bookLabel());

        Person p = new Person();
        p.runOnce().run();
    }

    List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(2, 1005));
        tickets.add(new Ticket(1, 1002));
        tickets.add(new Ticket(2, 1001));
        tickets.add(new Ticket(3, 1009));

        TicketSorter sorter = new TicketSorter();

        System.out.println("Оригінал: " + tickets);

        sorter.sort(tickets, "priorityThenCreatedAt");
        System.out.println("Відсортовано (Priority -> CreatedAt): " + tickets);
}
