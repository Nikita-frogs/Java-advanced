public class ABMain {
    public static void main(String[] args) {
        A a = new B();

        Object obj = new Object();

        a.process(obj);
        a.process("hi");
    }
}
