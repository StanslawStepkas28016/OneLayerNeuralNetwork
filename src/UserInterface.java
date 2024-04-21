import DataUtils.DataParser;
import DataUtils.IOUtility;
import DataUtils.LanguageObject;
import NetworkUtils.NeuralNetworks;
import NetworkUtils.Perceptron;
import NetworkUtils.Tester;

import java.util.ArrayList;
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
        // NeuralNetworks.backupTrainSets = trainSets; // Dodanie train-set jako backup, przy testowaniu (więcej nad zmienną w klasie).
        final ArrayList<Perceptron> trainedPerceptrons = NeuralNetworks.trainPerceptronsWithTrainSets(perceptrons, trainSets, 25); // Lista wytrenowanych perceptronów (szerszy opis w klasie NeuralNetworks).

        do {
            final Scanner scanner = new Scanner(System.in);
            System.out.println("= = = Dostępne opcje = = =");
            System.out.println("1. Klasyfikacja tekstów w zbiorze testowym.");
            System.out.println("2. Klasyfikacja tekstu podanego przez użytkownika.");
            System.out.println("3. Wyjście z programu.");
            System.out.print("Wprowadź opcję : ");
            final int userInput = scanner.nextInt();

            if (userInput == 1) {
                final List<LanguageObject> testSets = ioUtility.readDirectories(testSetPath); // Czytanie katalogu test-set.
                LanguageObject.assignDoubleRatiosForLanguages(testSets); // Zamiana znaków na wektory wag liter w alfabecie.
                Tester.testForTestSet(trainedPerceptrons, testSets); // Przeprowadzenie testu dla zbioru testowego.
            } else if (userInput == 2) {
                do {
                    final Scanner textScanner = new Scanner(System.in);
                    System.out.print("Wprowadź tekst do klasyfikacji : ");
                    final String text = textScanner.nextLine();
                    final ArrayList<String> strings = new ArrayList<>();
                    strings.add(text);

                    ArrayList<LanguageObject> userTestSets = new ArrayList<>();
                    userTestSets.add(new LanguageObject("user_lang", strings)); // Przypisanie obiektu do test-set użytkownika.
                    LanguageObject.assignDoubleRatiosForLanguages(userTestSets); // Przypisanie wag liter dla tekstu użytkownika.
                    Tester.testForTestSet(trainedPerceptrons, userTestSets); // Test na zbiorze od użytkownika.

                    final Scanner intScanner = new Scanner(System.in);
                    System.out.print("Czy chcesz podać tekst kolejny tekst (1 - Tak, 0 - Nie)? : ");
                    final int continueInt = intScanner.nextInt();

                    if (continueInt == 0) {
                        break;
                    }
                } while (true);
            } else if (userInput == 3) {
                break;
            } else {
                System.out.println("\nWprowadzono niepoprawną opcję, wprowadź opcję jeszcze raz!\n");
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