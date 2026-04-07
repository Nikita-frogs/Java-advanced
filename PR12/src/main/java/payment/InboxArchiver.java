package payment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

public class InboxArchiver {
    public static List<Path> archiveTmpFiles(Path inbox, Path archive) throws IOException {
        Files.createDirectories(archive);

        try (Stream<Path> entries = Files.list(inbox)) {
            List<Path> tmpFiles = entries
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".tmp"))
                    .toList();

            for (Path source : tmpFiles) {
                Path destination = archive.resolve(source.getFileName());
                Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.printf("  Moved: %s → %s%n", source.getFileName(), archive);
            }

            return tmpFiles.stream()
                    .map(Path::getFileName)
                    .toList();
        }
    }
}
