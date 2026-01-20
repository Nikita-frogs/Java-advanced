import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    @Test
    void accountCheck() {
        BankAccount account = new BankAccount("AE17847843",1000);
        assertEquals(1000, account.getBalance());
        assertEquals("AE17847843", account.getAccountNumber());
    }

    @Test
    void depositWithdrawCheck() {
        BankAccount account = new BankAccount("AE17847843", 1000);
        account.deposit(500);
        assertEquals(1500, account.getBalance());

        account.withdraw(300);
        assertEquals(1200, account.getBalance());
    }

    @Test
    void depositWithdrawFail() {
        BankAccount account = new BankAccount("AE17847843", 1000);

        Exception depositException = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100);
        });
        assertEquals("Deposit amount must be positive", depositException.getMessage());

        Exception withdrawException = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(2000);
        });
        assertEquals("Invalid withdrawal amount", withdrawException.getMessage());
    }
}

