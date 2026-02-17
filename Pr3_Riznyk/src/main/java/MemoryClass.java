public class MemoryClass {
    public static void run() {
        int num = 100;
        int num1 = num;
        String person = "Alice";
        String person1 = person;
        int[] arr = {1, 2, 3};
        int[] arr1 = arr;

        System.out.println(num);
        System.out.println(num1);

        System.out.println(person);
        System.out.println(person1);

        for(int n : arr) {
            System.out.print(n + " ");
        }
        for(int n : arr1) {
            System.out.print(n + " ");
        }

        //Objects are stored in heap memory and referenced in stack memory.
        //Primitives are stored directly in stack memory.
    }

    public static void main(String[] args) {
        run();
    }
}
