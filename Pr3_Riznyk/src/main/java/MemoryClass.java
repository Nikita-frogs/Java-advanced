public class MemoryClass {
    public static void run() {
        int num = 100;
        int num1 = num;
        String person = "Alice";
        String person1 = person;

        System.out.println(num);
        System.out.println(num1);

        System.out.println(person);
        System.out.println(person1);
    }

    public static void main(String[] args) {
        run();
    }
}
