package Refactoring_anonymus_lambda;

import java.util.Comparator;
import java.util.List;

public class TicketSorter {

    public void sort(List<Ticket> tickets, String strategy) {
        switch (strategy) {
            case "anonymous":
                // Етап 1: Anonymous Class (старий і багатослівний спосіб)
                tickets.sort(new Comparator<Ticket>() {
                    @Override
                    public int compare(Ticket t1, Ticket t2) {
                        return Integer.compare(t1.getPriority(), t2.getPriority());
                    }
                });
                break;

            case "lambda":
                // Етап 2: Рефакторинг на Лямбду (вже краще)
                tickets.sort((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()));
                break;

            case "priority":
                // Етап 3: Method Reference (через константу)
                tickets.sort(TicketComparators.BY_PRIORITY);
                break;

            case "createdAt":
                tickets.sort(TicketComparators.BY_CREATED_AT);
                break;

            case "priorityThenCreatedAt":
                // Етап 4: Комбіноване сортування
                tickets.sort(TicketComparators.BY_PRIORITY_THEN_CREATED_AT);
                break;

            default:
                throw new IllegalArgumentException("Невідома стратегія сортування: " + strategy);
        }
    }
}
