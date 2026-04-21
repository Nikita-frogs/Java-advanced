public class Main {
    public static void main(String[] args) {
        Book b = new Book("IT","Steven King",225);
        Class<?> clazz = Book.class;
        ClassInfoPrinter.print(clazz);
    }
}
