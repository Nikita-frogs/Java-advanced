public class AntiPatterns {
    static class GodObject {
        void doEverything() {
        }
    }

    static void spaghetti(boolean flag, boolean otherFlag) {
        if (flag) {
            if (otherFlag) {
                System.out.println("A");
            } else {
                System.out.println("B");
            }
        } else {
            System.out.println("C");
        }
    }

    static void longMethod() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        for (int i = 0; i < 100; i++) {
            sum += i * i;
        }
        System.out.println(sum);
    }

    static class User {
        String email;
        String phone;
    }
}
