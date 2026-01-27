import java.util.Optional;

public class UserRepository {
    public static Optional<User> findByEmail(String email) {
        if("nikita@example.com".equals(email)) {
            User user = new User("Nikita");
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        User user = findByEmail("nikita@example.com").orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User found: " + user.getName());

        User user2 = findByEmail("alice@example.com").orElseGet(() -> new User("Unknown User"));
        System.out.println("User found: " + user2.getName());

        User user3 = findByEmail("no@user.com").orElseThrow(() -> new IllegalArgumentException("No user with this email"));
    }
}
