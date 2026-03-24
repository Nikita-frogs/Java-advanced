import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Завдання 1: Створення подій
        Event event1 = new Event("Java Keynote", LocalDateTime.of(2026, 3, 24, 10, 0), 90, "Europe/Kyiv", "Main Stage");
        Event event2 = new Event("Spring Boot Deep Dive", LocalDateTime.of(2026, 3, 24, 11, 0), 120, "Europe/Kyiv", "Backend");
        Event event3 = new Event("Frontend Tools", LocalDateTime.of(2026, 3, 24, 15, 0), 60, "Europe/Kyiv", "Frontend");
        // Подія в іншому часовому поясі (наприклад, онлайн спікер)
        Event event4 = new Event("Cloud Native Architecture", LocalDateTime.of(2026, 3, 24, 9, 0), 60, "Europe/London", "Architecture");

        List<Event> schedule = Arrays.asList(event1, event4, event3, event2);

        // Завдання 2: Функціональні інтерфейси
        System.out.println("--- Усі мітки ---");
        List<String> labels = EventLab.getLabels(schedule, Event::label);
        labels.forEach(System.out::println);

        // Предикат для ранкових подій (до 12:00 за локальним часом події)
        Predicate<Event> isMorning = e -> e.getStart().getHour() < 12;

        // Предикат для бекенд-треку
        Predicate<Event> isBackend = e -> e.getTrack().equals("Backend");

        // Композиція предикатів (Ранкова І Бекенд) АБО (НЕ Ранкова)
        Predicate<Event> complexFilter = isMorning.and(isBackend).or(isMorning.negate());

        System.out.println("\n--- Відфільтровані події (складний предикат) ---");
        List<Event> filtered = EventLab.pick(schedule, complexFilter);
        EventLab.notifyAll(filtered, e -> System.out.println("Запрошуємо на: " + e.getTitle()));

        // Пошук конфліктів
        System.out.println();
        EventLab.findConflicts(schedule); // Виявить конфлікт між Keynote (10:00-11:30) та Spring Boot (11:00-13:00)

        // Завдання 3: Сортування
        System.out.println("\n--- Сортування (Method Reference) ---");
        LambdaRefactorLab.sortMethodRef(schedule);
        schedule.forEach(e -> System.out.println(e.getTitle() + " о " + e.getStart()));

        // Завдання 4: Java Time
        System.out.println("\n--- Java Time API ---");
        System.out.println("Час між Keynote та Frontend Tools: " +
                DateTimeLab.minutesBetween(event1, event3) + " хвилин");

        System.out.println("Початок 'Cloud Native' за Київським часом: " +
                DateTimeLab.startInZone(event4, "Europe/Kyiv"));

        // Використання предикатів
        System.out.println("\n--- Сортування (isMorning) ---");
        EventLab.pick(schedule, isMorning).forEach(System.out::println);
        System.out.println("\n--- Сортування (complexFilter) ---");
        EventLab.pick(schedule, complexFilter).forEach(System.out::println);
    }
}