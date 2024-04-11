import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class IOUtility {
    private final String targetDirectory;

    public IOUtility(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public List<String> readDirectory() {
        final List<String> fileContents = new ArrayList<>();
        try {
            Files.walkFileTree(Path.of(this.targetDirectory), new SimpleFileVisitor<>() {
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
