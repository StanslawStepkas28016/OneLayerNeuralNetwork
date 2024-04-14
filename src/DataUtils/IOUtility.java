package DataUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IOUtility {

    public List<LanguageObject> readDirectories(String mainDirectory) {
        final List<LanguageObject> languageObjects = new ArrayList<>();
        final HashMap<String, List<String>> languageMapper = new HashMap<>();

        try {
            Files.walkFileTree(Path.of(mainDirectory), new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    final String parentName = file.getParent().getFileName().toString();
                    final String string = Files.readString(file);

                    languageMapper.putIfAbsent(parentName, new ArrayList<>());
                    languageMapper.get(parentName).add(string);

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        languageMapper.forEach((language, files) -> languageObjects.add(new LanguageObject(language, files)));

        return languageObjects;
    }
}
