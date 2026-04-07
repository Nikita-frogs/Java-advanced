package payment;

import java.util.List;

public record LoadResult(List<Payment> payments, int invalidLines) {
    public LoadResult {
        if (payments == null) throw new IllegalArgumentException("payments list must not be null");
        if (invalidLines < 0) throw new IllegalArgumentException("invalidLines must not be negative");
        payments = List.copyOf(payments); // make immutable
    }
}
