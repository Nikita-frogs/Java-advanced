import java.util.ArrayList;
import java.util.List;

public class GenericMethods_Bounds {

    public static <T> T firstOrNull(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static double sum(List<? extends Number> list) {
        double total = 0.0;
        for (Number n : list) {
            total += n.doubleValue(); // Усі Number мають метод doubleValue()
        }
        return total;
    }

    public static void addDefaultIds(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public static void main(String[] args) {
        List<String> names = List.of("Олексій", "Марія");
        System.out.println("Перший елемент: " + firstOrNull(names));
        System.out.println("Пустий список: " + firstOrNull(new ArrayList<>()));

        List<Integer> ints = List.of(10, 20, 30);
        List<Double> doubles = List.of(1.5, 2.5, 3.5);
        System.out.println("Сума Integer: " + sum(ints));
        System.out.println("Сума Double: " + sum(doubles));

        List<Number> numList = new ArrayList<>();
        addDefaultIds(numList);
        System.out.println("Список з ID: " + numList);
    }
}