import java.util.*;

public class StudentRegistry {
    List<Student> studentsList;
    Set<String> emailsSet;
    Map<String, Student> idMap;

    public StudentRegistry(){
        this.studentsList = new ArrayList<>();
        this.emailsSet = new HashSet<>();
        this.idMap = new HashMap<>();
    }

    public boolean addStudent(Student student) {
        if (containsEmail(student.getEmail())||idMap.containsKey(student.getId())) {
            return false;
        }

        studentsList.add(student);
        emailsSet.add(student.getEmail());
        idMap.put(student.getId(), student);
        return true;
    }

    public Student findById(String id) {
        return idMap.get(id);
    }

    public boolean containsEmail(String email) {
        return emailsSet.contains(email);
    }

    public void removeById(String id) {
        Student student = idMap.get(id);
        if (student != null) {
            idMap.remove(id);
            studentsList.remove(student);
            emailsSet.remove(student.getEmail());
        }
    }
}
