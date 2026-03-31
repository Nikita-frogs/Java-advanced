public record Failure(String message) implements Result {
    @Override
    public String message() {
        return "Failure";
    }
}
