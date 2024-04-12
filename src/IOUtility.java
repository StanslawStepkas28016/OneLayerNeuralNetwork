import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class IOUtility {
    private final String mainDirectory;

    public IOUtility(String mainDirectory) {
        this.mainDirectory = mainDirectory;
    }

    public List<String> readDirectory(String targetDirectory) {
        final List<String> fileContents = new ArrayList<>();

        try {
            Files.walkFileTree(Path.of(STR."\{mainDirectory}/\{targetDirectory}"), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    final String string = Files.readString(file);
                    fileContents.add(string);

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileContents;
    }
}
