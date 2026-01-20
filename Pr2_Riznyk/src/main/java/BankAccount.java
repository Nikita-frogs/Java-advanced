public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        if(initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        double balance = this.balance;
        return balance;
    }

    public void deposit(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
        System.out.println("Deposited " + amount + " from bank account " + this.accountNumber);
        System.out.println("Balance: " + this.balance);
    }

    public void withdraw(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if(amount > this.balance) {
            throw new IndexOutOfBoundsException("Insufficient funds for withdrawal");
        }
        this.balance -= amount;
        System.out.println("Withdrawn " + amount + " from bank account " + this.accountNumber);
        System.out.println("Balance: " + this.balance);
    }
}
