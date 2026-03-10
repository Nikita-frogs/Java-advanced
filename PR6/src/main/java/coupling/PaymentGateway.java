import java.util.Objects;

class PaymentDetails {
    private final String token;

    public PaymentDetails(String token) {
        this.token = Objects.requireNonNull(token, "token");
    }

    public String token() {
        return token;
    }
}

class PaymentResult {
    private final boolean success;
    private final String message;

    private PaymentResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static PaymentResult ok() {
        return new PaymentResult(true, "OK");
    }

    public static PaymentResult fail(String message) {
        return new PaymentResult(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return success ? "OK" : "FAIL: " + message;
    }
}

class StripePaymentGateway implements TightCheckoutProcessor.PaymentGateway {
    @Override
    public PaymentResult charge(Money amount, PaymentDetails details) {
        return PaymentResult.ok();
    }
}

class FakePaymentGateway implements TightCheckoutProcessor.PaymentGateway {
    private boolean fail;

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    @Override
    public PaymentResult charge(Money amount, PaymentDetails details) {
        return fail ? PaymentResult.fail("Card declined") : PaymentResult.ok();
    }
}
