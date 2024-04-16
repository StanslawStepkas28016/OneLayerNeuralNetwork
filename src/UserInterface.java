import DataUtils.DataParser;
import DataUtils.IOUtility;
import DataUtils.LanguageObject;
import NetworkUtils.NeuralNetworks;
import NetworkUtils.Perceptron;
import NetworkUtils.Tester;
import NetworkUtils.Trainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        final List<Perceptron> trainedPerceptrons = NeuralNetworks.trainPerceptronsWithTrainSets(perceptrons, new Trainer(), trainSets, 10); // Lista wytrenowanych perceptronów (szerszy opis w klasie NeuralNetworks).

        //printDoubleRatiosForLanguages(trainSets);

        final List<LanguageObject> testSets = ioUtility.readDirectories(testSetPath); // Czytanie katalogu test-set.
        LanguageObject.assignDoubleRatiosForLanguages(testSets); // Zamiana znaków na wektory wag liter w alfabecie.
        Tester.testForTestSet(trainedPerceptrons, testSets); // Przeprowadzenie testu dla zbioru testowego.

        final Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Wprowadź tekst do klasyfikacji: ");
            final String text = sc.next();

            final ArrayList<String> strings = new ArrayList<>();
            strings.add(text);
            ArrayList<LanguageObject> userTestSet = new ArrayList<>();
            userTestSet.add(new LanguageObject("user_lang", strings));
            LanguageObject.assignDoubleRatiosForLanguages(userTestSet);
            Tester.testForTestSet(trainedPerceptrons, userTestSet); // Test na zbiorze od użytkownika.

            System.out.println("Czy chcesz podać tekst jeszcze raz (1 - Tak, 0 - Nie)?");
            final int i = sc.nextInt();

            if (i == 1) {
                break;
            }
        } while (true);
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