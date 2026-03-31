sealed interface Result permits Failure, Success {
    String message();
}
