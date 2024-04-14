package NetworkUtils;

import DataUtils.LanguageObject;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetworks {
    /* Metoda zwraca "puste" (bez ustawionego języka i zmodyfikowanych wag) obiekty klasy Perceptron. */
    public static ArrayList<Perceptron> assignPerceptonsToNetwork(int layersQuantity, double learnRate) {
        final ArrayList<Perceptron> neuralNetwork = new ArrayList<>();

        for (int i = 0; i < layersQuantity; i++) {
            // Ustalamy rozmiar na 26, bo klasyfikujemy po alfabecie, który ma 26 liter [A-za-az].
            neuralNetwork.add(new Perceptron(26, learnRate));
        }

        return neuralNetwork;
    }

    /* Metoda trenuje każdy perceptron, korzystając ze wszystkich języków, jednocześnie zwracając uwagę na targetLang,
     * który, po odpowiednim spreparowaniu determinuje, czy będzie wykonywana deltaRule dla wag perceptronu. */
    public static List<Perceptron> trainPerceptronsWithTrainSets(ArrayList<Perceptron> perceptrons,
                                                                 Trainer trainer,
                                                                 List<LanguageObject> allLangTrainSets,
                                                                 int epoqueCount) {
        // Każdy perceptron jest trenowany każdym językiem (train setem, dla każdego języka),
        // ale używany jest target language, który będzie determinował, czy będzie wykonywana reguła delta.
        for (int j = 0; j < epoqueCount; j++) {
            int i = 0;
            for (Perceptron singlePerceptron : perceptrons) {
                final String targetLang = allLangTrainSets.get(i).getLanguage(); // Zmienna wykorzystywana przy uczeniu (komentarz wyżej).

                if (singlePerceptron.getLanguage() == null) {
                    singlePerceptron.setLanguage(targetLang); // Ustawienie języka, który będzie językiem głównym danego perceptronu (wykonywane tylko raz).
                }

                trainer.train(allLangTrainSets, singlePerceptron, targetLang);
                i++;
            }
        }

        return perceptrons;
    }

    public static void trainOnePerceptronWithTrainSets(Perceptron perceptron, Trainer trainer, List<LanguageObject> allLangTrainSets, int epoqueCount) {
        for (int j = 0; j < epoqueCount; j++) {
            final String targetLang = perceptron.getLanguage();
            trainer.train(allLangTrainSets, perceptron, targetLang);
        }
    }

}
