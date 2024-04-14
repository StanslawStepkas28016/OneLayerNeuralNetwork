package NetworkUtils;

import DataUtils.LanguageObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NeuralNetworks {
    public static ArrayList<Perceptron> assignPerceptonsToNetwork(int layersQuantity, double learnRate) {
        final ArrayList<Perceptron> neuralNetwork = new ArrayList<>();

        for (int i = 0; i < layersQuantity; i++) {
            // Ustalamy rozmiar na 26, bo klasyfikujemy po alfabecie, który ma 26 liter [A-za-az].
            neuralNetwork.add(new Perceptron(26, learnRate));
        }

        return neuralNetwork;
    }

    /* Metoda dodaje puste obiekty Trainer, będę one potem oddzielnie nauczane. */
    public static ArrayList<Trainer> assignUntrainedTrainers(int size) {
        final ArrayList<Trainer> trainers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            trainers.add(new Trainer());
        }

        return trainers;
    }

    public static void trainPerceptronsWithTrainSets(ArrayList<Perceptron> perceptrons, ArrayList<Trainer> trainers, List<LanguageObject> trainSets) {

    }
}
