

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscountPolicyTest {
    @ParameterizedTest
    @CsvSource({
            "100.00, 10.0, 90.00",
            "100.00, 0.0, 100.00",
            "100.00, 100.0, 0.00"
    })
    void percentDiscountCalculates(double price, double percent, double expected) {
        DiscountPolicy policy = new PercentDiscountPolicy(percent);
        Money result = policy.apply(Money.ofDollars(price));
        assertEquals(Money.ofDollars(expected), result);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, 120.0})
    void percentDiscountRejectsInvalidValues(double percent) {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscountPolicy(percent));
    }

    @ParameterizedTest
    @CsvSource({
            "0.00, 0.0",
            "100.00, 0.0",
            "100.00, 10.0",
            "100.00, 100.0",
            "999999.99, 15.0"
    })
    void percentDiscountRespectsContract(double price, double percent) {
        DiscountPolicy policy = new PercentDiscountPolicy(percent);
        Money base = Money.ofDollars(price);
        Money result = policy.apply(base);
        assertTrue(result.cents() >= 0);
        assertTrue(result.cents() <= base.cents());
    }

    @ParameterizedTest
    @CsvSource({
            "0.00",
            "100.00",
            "999999.99"
    })
    void noDiscountRespectsContract(double price) {
        DiscountPolicy policy = new NoDiscountPolicy();
        Money base = Money.ofDollars(price);
        Money result = policy.apply(base);
        assertTrue(result.cents() >= 0);
        assertTrue(result.cents() <= base.cents());
    }
}
