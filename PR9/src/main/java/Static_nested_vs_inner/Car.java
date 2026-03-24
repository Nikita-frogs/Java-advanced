package Static_nested_vs_inner;

public class Car {
    private String model;
    public static Engine engineSpec(){
        Engine engine = new Engine();
        engine.horsepower = 560;
        return engine;
    }
    public static class Engine{
        private int horsepower;
        public String toString() {
            return "Engine " + horsepower;
        }
    }
}
