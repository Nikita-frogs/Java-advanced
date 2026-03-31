import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
        System.out.println("\n--- 1. Сортування перших трьох книг ---");
        List<String> processedBooks = books.stream()
                .filter(book -> book.year() > 2015)
                .map(book -> book.title().toUpperCase())
                .sorted()
                .limit(3)
                .toList();

        // Виведення результату
        processedBooks.forEach(System.out::println);

        System.out.println("\n--- 2. Аналітика тегів (Топ-N) ---");
        // Будуємо Map<String, Long> (тег -> кількість появ)
        Map<String, Long> tagFrequency = books.stream()
                .flatMap(book -> book.tags().stream())
                .collect(Collectors.groupingBy(
                        tag -> tag,
                        Collectors.counting()
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

        System.out.println("\n--- 3. Collectors pipeline ---");
        // 1. Створення списку Sale
        List<Sale> sales = List.of(
                new Sale("Laptop", 1200, "alice@example.com"),
                new Sale("Mouse", 25, "bob@example.com"),
                new Sale("Laptop", 1200, "bob@example.com"),
                new Sale("Keyboard", 75, "alice@example.com"),
                new Sale("Mouse", 25, "alice@example.com"),
                new Sale("Monitor", 300, "charlie@example.com")
        );

        System.out.println("--- 1. Сумарна виручка за продуктами ---");
        Map<String, Integer> productRevenue = sales.stream()
                .collect(Collectors.toMap(
                        Sale::product,
                        Sale::revenue,
                        Integer::sum
                ));

        productRevenue.forEach((product, total) ->
                System.out.println(product + " : $" + total)
        );

        System.out.println("\n--- 2. Кількість транзакцій за клієнтами ---");
        Map<String, Long> transactionsPerCustomer = sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::customerEmail,
                        Collectors.counting()
                ));

        transactionsPerCustomer.forEach((email, count) ->
                System.out.println(email + " : " + count + " транзакцій")
        );

        System.out.println("\n--- 4. Sealed higherarchy ---");
        List<Result> results = List.of(
                new Success("Data uploaded"),
                new Failure("Failed to connect to database"),
                new Success("Data updated"),
                new Failure("Failed to message user"),
                new Success("Data deleted"),
                new Failure("Max limit reached"),
                new Success("Data sent")
        );

        Map<Boolean, Long> processedResults = results.stream()
                .collect(Collectors.partitioningBy(
                        result -> result instanceof Success,
                        Collectors.counting()
                ));

        long successCount = processedResults.get(true);
        long failureCount = processedResults.get(false);

        System.out.println("Кількість успіхів: " + successCount);
        System.out.println("Кількість помилок: " + failureCount);

        List<String> errorMessages = results.stream()
                .filter(result -> result instanceof Failure)
                .map(result -> ((Failure) result).message())
                .toList();

        errorMessages.forEach(System.out::println);

        System.out.println("\n--- 5. Advanced collectors ---");
        Map<Boolean, List<Book>> recentVsOld = books.stream()
                .collect(Collectors.partitioningBy(b -> b.year() > 2015));

        System.out.println("--- Результат partitioningBy (true - після 2015) ---");
        recentVsOld.forEach((isRecent, bookList) ->
                System.out.println(isRecent + ": " + bookList));

        Map<String, Integer> productRevenueSorted = sales.stream()
                .collect(Collectors.toMap(
                        Sale::product,
                        Sale::revenue,
                        Integer::sum,
                        TreeMap::new
                ));

        System.out.println("\n--- Виручка за продуктами (відсортовано за назвою) ---");
        productRevenueSorted.forEach((product, total) ->
                System.out.println(product + " : $" + total)
        );
    }
}