package payment;

import java.io.IOException;
import java.nio.file.Path;

public class PathSafety {
    public static Path safeResolve(Path base, Path userInput) {
        Path normalizedBase = base.toAbsolutePath().normalize();
        Path resolved = normalizedBase.resolve(userInput).normalize();

        if (!resolved.startsWith(normalizedBase)) {
            throw new IllegalArgumentException(
                    "Path traversal detected! Input escapes base directory.\n" +
                            "  base     : " + normalizedBase + "\n" +
                            "  input    : " + userInput + "\n" +
                            "  resolved : " + resolved
            );
        }

        return resolved;
    }
}
