package NetworkUtils;

import DataUtils.LanguageObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {
    public static void testForTestSet(List<Perceptron> trainedPerceptrons, List<LanguageObject> testSets) {
        for (LanguageObject testSet : testSets) {
            final HashMap<String, Integer> langToYMap = new HashMap<>(); // Mapa do określenia języka.
            final List<double[]> doublesForFiles = testSet.getDoublesForFiles(); // Lista wektorów dla plików z jednego katalogu zbioru testowego.

            for (Perceptron trainedPerceptron : trainedPerceptrons) {
                for (double[] vecForFile : doublesForFiles) {
                    double net = 0.0;

                    for (int i = 0; i < vecForFile.length; i++) {
                        net += vecForFile[i] * trainedPerceptron.weights[i];
                    }

                    net -= trainedPerceptron.tVal;

                    int y = net >= 0 ? 1 : 0;
                    langToYMap.put(trainedPerceptron.getLanguage(), y);
                }
            }

            // Największa wartość y określa nam dany język (maximum selector).
            // Dodać trenowanie dla jednego perceptronu, jeżeli nie ma max val w mapie... (!)
            System.out.println(STR."FOR DATA : \{testSet.getLanguage()}, LANG COMPUTED : \{langToYMap}");
        }

    }
}
