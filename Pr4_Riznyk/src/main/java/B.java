public class B extends A {
    @Override
    void process(Object obj) {
        System.out.println("Processing B object");
    }

    void process(String str) {
        System.out.println("Processing B string");
    }
}
