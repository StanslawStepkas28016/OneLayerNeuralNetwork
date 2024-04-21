package NetworkUtils;

import DataUtils.LanguageObject;

import java.util.*;

public class Tester {
    public static void testForTestSet(ArrayList<Perceptron> trainedPerceptrons, List<LanguageObject> testSets) {
        for (LanguageObject testSet : testSets) {
            final HashMap<String, Integer> langToYMap = outsMap(trainedPerceptrons, testSet);

            // Największa wartość y określa nam dany język (maximum selector).
            System.out.println(STR."FOR DATA : \{testSet.getLanguage()}, LANG COMPUTED : \{langToYMap}");
        }
    }

    private static HashMap<String, Integer> outsMap(ArrayList<Perceptron> trainedPerceptrons, LanguageObject testSet) {
        final HashMap<String, Double> langToNetMap = new HashMap<>(); // Mapa do określenia języka.
        final List<double[]> doublesForFiles = testSet.getDoublesForFiles(); // Lista wektorów dla plików z jednego katalogu zbioru testowego.

        for (Perceptron trainedPerceptron : trainedPerceptrons) {
            for (double[] vecForFile : doublesForFiles) {
                double net = 0.0;

                for (int i = 0; i < vecForFile.length; i++) {
                    net += vecForFile[i] * trainedPerceptron.weights[i];
                }

                // net -= trainedPerceptron.tVal;

                //int y = net >= 0 ? 1 : 0;
                langToNetMap.put(trainedPerceptron.getLanguage(), net);
            }
        }

        return finalOutsMap(langToNetMap);
    }

    private static HashMap<String, Integer> finalOutsMap(HashMap<String, Double> langToNetMap) {
        // Największa wartość w mapie, będzie ona "aktywować" funkcję.
        String languageWithMaxNet = Collections.max(langToNetMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        // Finalna mapa, która zawiera aktywowane i nieaktywowane perceptrony.
        HashMap<String, Integer> finalMap = new HashMap<>();
        langToNetMap.forEach((language, netValue) -> {
            finalMap.put(language, language.equals(languageWithMaxNet) ? 1 : 0);
        });
        return finalMap;
    }

    private static double[] normalizeVector(double[] vector) {
        double norm = Math.sqrt(Arrays.stream(vector).map(v -> v * v).sum());
        return Arrays.stream(vector).map(v -> v / norm).toArray();
    }

}
