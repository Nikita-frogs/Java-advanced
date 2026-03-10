import java.util.Objects;

public class Student {
    private String name;
    private String id;
    private String email;

    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Student[id=%s, name=%s, email=%s]", id, name, email);
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        // 1. Перевірка на тотожність посилань
        if (this == o) return true;
        // 2. Перевірка на null та відповідність класів
        if (o == null || getClass() != o.getClass()) return false;
        // 3. Порівняння значущих полів
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        // Хеш-код генерується на основі тих самих полів, що й equals
        return Objects.hash(id);
    }
}
