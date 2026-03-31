public record Success(String data) implements Result {
    @Override
    public String message() {
        return "Success";
    }
}
