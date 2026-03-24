package Refactoring_anonymus_lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 1. Клас Ticket
public class Ticket {
    private int priority;
    private long createdAt;

    public Ticket(int priority, long createdAt) {
        this.priority = priority;
        this.createdAt = createdAt;
    }

    public int getPriority() { return priority; }
    public long getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "{p=" + priority + ", time=" + createdAt + "}";
    }
}

// 2. Утилітарний клас з компараторами
class TicketComparators {

    public static class LegacyPriorityComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket t1, Ticket t2) {
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
    }

    public static final Comparator<Ticket> BY_PRIORITY = Comparator.comparingInt(Ticket::getPriority);
    public static final Comparator<Ticket> BY_CREATED_AT = Comparator.comparingLong(Ticket::getCreatedAt);

    public static final Comparator<Ticket> BY_PRIORITY_THEN_CREATED_AT =
            BY_PRIORITY.thenComparing(BY_CREATED_AT);
}

