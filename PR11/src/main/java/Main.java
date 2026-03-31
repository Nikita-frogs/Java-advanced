import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Створення списку Book
        List<Book> books = List.of(
                new Book("Effective Java", 2018, List.of("java", "programming", "best practices")),
                new Book("Clean Code", 2008, List.of("programming", "architecture", "best practices")),
                new Book("Modern Java in Action", 2018, List.of("java", "spring", "backend", "framework")),
                new Book("Grokking Algorithms", 2016, List.of("algorithms", "programming", "basics")),
                new Book("Designing Data-Intensive Applications", 2017, List.of("java", "multithreading", "advanced")),
                new Book("Refactoring", 2018, List.of("java", "clean code", "best practices")),
                new Book("Java Concurrency in Practice", 2006, List.of("java", "programming", "best practices"))
        );

        // 2-4. Stream pipeline
        List<String> processedBooks = books.stream()
                .filter(book -> book.year() > 2015)         // Відфільтровуємо книги після 2015 року
                .map(book -> book.title().toUpperCase())    // Перетворюємо назви у верхній регістр
                .sorted()                                   // Сортуємо за алфавітом
                .limit(3)                                   // Залишаємо лише 3 перших елементи
                .toList();                                  // Збираємо результат у список

        // Виведення результату
        processedBooks.forEach(System.out::println);

        System.out.println("\n--- 2. Аналітика тегів (Топ-N) ---");
        // Будуємо Map<String, Long> (тег -> кількість появ)
        Map<String, Long> tagFrequency = books.stream()
                .flatMap(book -> book.tags().stream())
                .collect(Collectors.groupingBy(
                        tag -> tag,                    // Ключ - сам тег
                        Collectors.counting()          // Значення - кількість
                ));

        int n = 3;
        List<Map.Entry<String, Long>> topTags = tagFrequency.entrySet().stream()
                // Сортуємо спочатку за частотою (спадання), потім за назвою (зростання)
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .toList();

        topTags.forEach(entry ->
                System.out.println(entry.getKey() + " : " + entry.getValue())
        );
    }
}