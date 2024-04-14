import DataUtils.DataParser;
import DataUtils.IOUtility;
import DataUtils.LanguageObject;
import NetworkUtils.NeuralNetworks;
import NetworkUtils.Perceptron;
import NetworkUtils.Tester;
import NetworkUtils.Trainer;

import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    public static void main(String[] args) {
        final String trainSetPath = args[0]; // Zbiór treningowy.
        final String testSetPath = args[1]; // Zbiór testowy.
        final double learnRate = Double.parseDouble(args[2]); // Stała uczenia.

        final IOUtility ioUtility = new IOUtility(); // Obiekt klasy IOUtility.
        final List<LanguageObject> trainSets = ioUtility.readDirectories(trainSetPath); // Czytanie katalogu train-set.
        LanguageObject.assignDoubleRatiosForLanguages(trainSets); // Zamiana znaków na wektory wag liter w alfabecie.

        final int layersQuantity = trainSets.size(); // Ilość warstw sieci neuronowej.
        final ArrayList<Perceptron> perceptrons = NeuralNetworks.assignPerceptonsToNetwork(layersQuantity, learnRate); // Przypisanie perceptronów do sieci.
        final List<Perceptron> trainedPerceptrons = NeuralNetworks.trainPerceptronsWithTrainSets(perceptrons, new Trainer(), trainSets, 1); // Lista wytrenowanych perceptronów (szerszy opis w klasie NeuralNetworks).

        printDoubleRatiosForLanguages(trainSets);

        final List<LanguageObject> testSets = ioUtility.readDirectories(testSetPath); // Czytanie katalogu test-set.
        LanguageObject.assignDoubleRatiosForLanguages(testSets); // Zamiana znaków na wektory wag liter w alfabecie.
        Tester.testForTestSet(trainedPerceptrons, testSets); // Przeprowadzenie testu dla zbioru testowego.


        //printLanguageObjects(trainSet);
        //printDoubleRatiosForLanguages(trainSet);

    }

    private static void printDoubleRatiosForLanguages(List<LanguageObject> languageObjects) {
        for (LanguageObject languageObject : languageObjects) {
            System.out.println(languageObject.doubleRatiosToString());
        }
    }

    private static void printLanguageObjects(List<LanguageObject> languageObjects) {
        for (LanguageObject languageObject : languageObjects) {
            System.out.println(languageObject);
        }
    }

    private static void printAlphabetIndices() {
        System.out.println(STR."Przypomienie : \{DataParser.getAlphabetMap()}\n");
    }
}