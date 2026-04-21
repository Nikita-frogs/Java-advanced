package annotation_runner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StepRunner {

    public static void run(Object target) {
        Class<?> clazz = target.getClass();
        Method[] methods = clazz.getDeclaredMethods(); // Включає private методи
        List<Method> stepMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Step.class)) {
                if (method.getReturnType() != void.class || method.getParameterCount() != 0) {
                    throw new StepExecutionException(
                            "Невалідна сигнатура! Метод " + method.getName() +
                                    " повинен повертати void та не мати аргументів."
                    );
                }
                stepMethods.add(method);
            }
        }

        stepMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Step.class).order()));

        for (Method method : stepMethods) {
            method.trySetAccessible();

            try {
                method.invoke(target);
            } catch (InvocationTargetException e) {
                throw new StepExecutionException(
                        "Помилка під час виконання кроку: " + method.getName(),
                        e.getCause()
                );
            } catch (IllegalAccessException e) {
                throw new StepExecutionException("Немає доступу до методу: " + method.getName(), e);
            }
        }
    }
}
