
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegistryTest {
    StudentRegistry registry;
    Student s;

    @BeforeEach
    void setUp() {
        registry = new StudentRegistry();
        s = new Student("Іван", "1", "ivan@test.com");
        registry.addStudent(s);
    }
    @Test
    void testRemoveByIdFullyCleansData() {
        registry.removeById("1");

        assertNull(registry.findById("1"));
        assertFalse(registry.containsEmail("ivan@test.com"));
        assertTrue(registry.addStudent(new Student("2", "Новий Іван", "ivan@test.com")));
    }
    @Test
    void findByIdFinds(){
        assertEquals(s, registry.findById("1"));
    }
    @Test
    void containsEmail(){
        assertTrue(registry.containsEmail("ivan@test.com"));
    }
}
