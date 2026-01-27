public class UsRepoRun {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        try {
            User user = userRepository.findByEmail("nikita@example.com").orElseThrow(() -> new IllegalArgumentException("User not found"));
            System.out.println("User found: " + user.getName());
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        User user2 = userRepository.findByEmail("alice@example.com").orElseGet(() -> new User("Unknown User"));
        System.out.println("User found: " + user2.getName());

        try {
            User user3 = userRepository.findByEmail("no@user.com").orElseThrow(() -> new IllegalArgumentException("No user with this email"));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
