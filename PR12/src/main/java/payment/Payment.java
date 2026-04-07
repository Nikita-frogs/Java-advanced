package payment;

public record Payment(long id, String email, PaymentStatus status, long amountCents) {
}
