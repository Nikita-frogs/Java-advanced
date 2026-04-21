package reflection_info;

public class ClassInfoPrinter {
    public static void print(Class<?> clazz){
        System.out.println(clazz.getName());
        System.out.println(clazz.getSuperclass());
        System.out.println(clazz.getInterfaces());
        System.out.println(clazz.getDeclaredFields());
        System.out.println(clazz.getDeclaredMethods());
    }
}
