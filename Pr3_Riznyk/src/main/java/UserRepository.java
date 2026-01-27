import java.util.Optional;

public class UserRepository {
    public Optional<User> findByEmail(String email) {
        if("nikita@example.com".equals(email)) {
            User user = new User("Nikita");
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
