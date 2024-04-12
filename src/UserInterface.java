import java.util.HashMap;
import java.util.List;

public class UserInterface {
    public static void main(String[] args) {
        // Czytanie plików.
        final IOUtility ioUtility = new IOUtility("files");
        final List<String> czechDocs = ioUtility.readDirectory("czech");
        final List<String> frenchDocs = ioUtility.readDirectory("french");
        final List<String> germanDocs = ioUtility.readDirectory("german");

        // Wyliczenie proporcji (na podstawie wystąpień).
        DataParser dataParser = new DataParser();
        final List<HashMap<String, Double>> czechRatio = dataParser.processDocs(czechDocs);
        final List<HashMap<String, Double>> frenchRatio = dataParser.processDocs(frenchDocs);
        final List<HashMap<String, Double>> germanRatio = dataParser.processDocs(germanDocs);

        System.out.println("CZ " + czechRatio);
        System.out.println("FR " + frenchRatio);
        System.out.println("GE " + germanRatio);
    }
}