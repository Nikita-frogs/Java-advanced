package service_factory;

public class ServiceFactory {
    public static Object create(String className){
        try {
            Class<?> clazz = Class.forName(className);

            return clazz.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found: " + className, e);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during creation of class: " + className, e);
        }
    }
}
