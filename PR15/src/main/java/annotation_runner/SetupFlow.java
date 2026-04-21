package annotation_runner;

public class SetupFlow {

    @Step(order = 2)
    private void loadConfiguration() {
        System.out.println("Крок 2: Завантаження конфігурації (private метод)");
    }

    @Step(order = 1)
    public void initDatabase() {
        System.out.println("Крок 1: Ініціалізація бази даних (public метод)");
    }

    @Step(order = 3)
    void startServer() {
        System.out.println("Крок 3: Запуск сервера (package-private метод)");
    }

    // Приклад невалідного методу
    /*
    @Step(order = 4)
    public String invalidMethod(int retryCount) {
        return "Fail";
    }
    */
}
