import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EventLab {

    public static List<Event> pick(List<Event> events, Predicate<Event> condition) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (condition.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static List<String> labels(List<Event> events, Function<Event, String> mapper) {
        List<String> result = new ArrayList<>();
        for (Event e : events) {
            result.add(mapper.apply(e));
        }
        return result;
    }

    public static void notifyAll(List<Event> events, Consumer<Event> action) {
        for (Event e : events) {
            action.accept(e);
        }
    }

    public static Event create(Supplier<Event> supplier) {
        return supplier.get();
    }

    public static void findConflicts(List<Event> events) {
        System.out.println("--- Перевірка конфліктів ---");
        for (int i = 0; i < events.size(); i++) {
            for (int j = i + 1; j < events.size(); j++) {
                Event a = events.get(i);
                Event b = events.get(j);

                Instant startA = DateTimeLab.toInstant(a);
                Instant endA = DateTimeLab.toInstant(a).plusSeconds(a.getDurationMinutes() * 60);
                Instant startB = DateTimeLab.toInstant(b);
                Instant endB = DateTimeLab.toInstant(b).plusSeconds(b.getDurationMinutes() * 60);

                if (startA.isBefore(endB) && startB.isBefore(endA)) {
                    System.out.println("😥😥😥 Знайдено конфлікт між: " + a.label() + " та " + b.label());
                }
            }
        }
    }
}