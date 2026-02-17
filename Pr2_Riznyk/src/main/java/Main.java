public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456789", 1000.0);
//        System.out.println("Initial Balance: " + account.getBalance());
//        account.deposit(500.0);
//        account.withdraw(200.0);
//        account.withdraw(1500.0);
//        account.deposit(-500.0);

        ArrayStat stats = new ArrayStat();
        int[] numbers = {3, 5, 1, 9, 6};
        int[] numbers2 = {3, 3, 3};
        System.out.println("Max: " + stats.findMax(numbers2));
        System.out.println("Min: " + stats.findMin(numbers2));
        System.out.println("Average: " + stats.calculateAverage(numbers2));
    }
}
