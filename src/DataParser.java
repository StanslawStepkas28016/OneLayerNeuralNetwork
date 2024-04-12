import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    public List<HashMap<String, Double>> processDocs(List<String> documents) {
        // Mapa wystąpień.
        List<HashMap<String, Double>> list = new ArrayList<>();

        // Filtracja tekstów.
        for (String docContents : documents) {
            HashMap<String, Double> counts = new HashMap<>();

            Pattern pattern = Pattern.compile("[A-za-z]");
            Matcher matcher = pattern.matcher(docContents);

            while (matcher.find()) {
                String letter = matcher.group().toLowerCase();
                counts.put(letter, counts.getOrDefault(letter, 0.0) + 1.0 / 26.0);
            }

            list.add(counts);
        }

        return list;
    }
}
