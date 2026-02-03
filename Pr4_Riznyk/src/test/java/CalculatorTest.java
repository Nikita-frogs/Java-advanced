import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private Calculator calculator = new Calculator();
    @Test
    public void shouldBe(){
        int n = calculator.divide(15,3);
        assertEquals(5, n);
    }
    @Test
    public void shouldThrowExceptionWhenDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
    }
}
