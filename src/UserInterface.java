import java.util.Arrays;
import java.util.List;

public class UserInterface {
    public static void main(String[] args) {
        // Czytanie plików.
        final IOUtility ioUtility = new IOUtility("files");
        final List<LanguageObject> languageObjects = ioUtility.readDirectories();

//        printLanguageObjects(languageObjects);

        for (LanguageObject languageObject : languageObjects) {
            final List<String> stringsFromFiles = languageObject.getStringsFromFiles();
            final List<double[]> doubles = DataParser.processDocs(stringsFromFiles);
            languageObject.setDoublesForFiles(doubles);
            System.out.println(languageObject.toStringDoubles());
        }
    }

    private static void printLanguageObjects(List<LanguageObject> languageObjects) {
        for (LanguageObject languageObject : languageObjects) {
            System.out.println(languageObject);
        }
    }

    private static void printRatios(List<double[]> langRatio, String lang) {
        System.out.print(STR."\{lang} : ");
        for (double[] doubles : langRatio) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    private static void printAlphabetIndices() {
        System.out.println(STR."Przypomienie : \{DataParser.getAlphabetMap()}\n");
    }
}