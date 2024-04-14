import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    public static HashMap<String, Integer> getAlphabetMap() {
        HashMap<String, Integer> alphabetMap = new HashMap<>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int index = 0;

        for (char letter : alphabet) {
            alphabetMap.put(String.valueOf(letter), index++);
        }

        return alphabetMap;
    }

    public static List<double[]> processDocs(List<String> documents) {
        // Lista list (wektor wektorów), dla wystąpień liter w danym pliku.
        List<double[]> vectors = new ArrayList<>();

        // Klucz (litera), Wartość (indeks w wektorze) a -> [0], b -> [1].
        HashMap<String, Integer> alphabetMap = getAlphabetMap();

        // Filtracja tekstów.
        for (String docContents : documents) {
            Pattern pattern = Pattern.compile("[A-za-z]");
            Matcher matcher = pattern.matcher(docContents);

            final double[] vector = new double[26];

            while (matcher.find()) {
                // Litera z reggexa.
                String letter = matcher.group().toLowerCase();

                // Indeks, na którym znajduje się dana litera w wektorze.
                final Integer vectorIndex = alphabetMap.get(letter);

                vector[vectorIndex] += 1.0;
            }

            for (int i = 0; i < vector.length; i++) {
                vector[i] /= 26.0;
            }

            vectors.add(vector);
        }

        return vectors;
    }
}
