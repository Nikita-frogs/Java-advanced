package payment;

public enum PaymentStatus {
    NEW,
    PAID,
    FAILED;

    public static PaymentStatus fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }
        return PaymentStatus.valueOf(value.trim().toUpperCase());
    }
}