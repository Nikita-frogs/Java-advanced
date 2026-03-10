import java.util.HashSet;
import java.util.Set;

public class StudentRegistryMain {
    public static void main(String[] args) {
        StudentRegistry registry = new StudentRegistry();

        Student s1 = new Student("Олена", "101", "olena@ua.com");
        Student s2 = new Student("Ігор", "102", "olena@ua.com"); // Дублікат email

        System.out.println("Додано Олену: " + registry.addStudent(s1));
        System.out.println("Додано Ігоря (дублікат email): " + registry.addStudent(s2));

        System.out.println("Чи є email olena@ua.com? " + registry.containsEmail("olena@ua.com"));

        System.out.println("\nВидаляємо Олену...");
        registry.removeById("101");

        System.out.println("Чи є email після видалення? " + registry.containsEmail("olena@ua.com"));

        System.out.println("Спроба додати нового студента з тим самим email...");
        boolean addedAgain = registry.addStudent(new Student("103", "Андрій", "olena@ua.com"));
        System.out.println("Успішно? " + addedAgain);


        Set<Student> set = new HashSet<>();

        Student s3 = new Student("Олена", "101", "olena@test.com");
        Student s4 = new Student("Олена (дублікат)", "101", "other@test.com");

        set.add(s3);
        set.add(s4);

        System.out.println("Розмір HashSet: " + set.size());
    }
}
