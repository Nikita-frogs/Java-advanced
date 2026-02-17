public class ChildInit extends BaseInit {
    static{
        System.out.println("ChildInit static block");
    }

    {
        System.out.println("ChildInit instance block");
    }
    public ChildInit(){
        System.out.println("ChildInit constructor");
    }
}
