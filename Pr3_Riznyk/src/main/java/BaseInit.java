public class BaseInit {
    static {
        System.out.println("BaseInit static block");
    }

    {
        System.out.println("BaseInit instance block");
    }

    public BaseInit() {
        System.out.println("BaseInit constructor");
    }
}
