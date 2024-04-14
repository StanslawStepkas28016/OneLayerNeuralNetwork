package NetworkUtils;

import DataUtils.LanguageObject;

import java.util.*;

public class Trainer {
    /* Każdy perceptron jest uczony danymi z każdego języka */
    public void train(List<LanguageObject> allLanguages, Perceptron perceptron, String targetLanguage) {
        for (LanguageObject lang : allLanguages) {
            final List<double[]> doublesForLanguage = lang.getDoublesForFiles();
            final int expectedY = lang.getLanguage().equals(targetLanguage) ? 1 : 0;

            // Obliczenie wyjścia na podstawie aktualnych wag oraz następującej
            // funkcji wyjścia (y >= 0 -> net = 1, y < 0 -> net = 0), wraz ze
            // sprawdzeniem zgodności wyjścia i przeprowadzeniem reguły Delta.
            for (double[] inputVector : doublesForLanguage) {
                double y = 0;

                for (int i = 0; i < inputVector.length; i++) {
                    y += (inputVector[i] * perceptron.weights[i]);
                }
                y -= perceptron.tVal;

                // Sprawdzenie, czy wymagane jest użycie reguły delta.
                int net = y >= 0 ? 1 : 0;
                if (net != expectedY) {
                    perceptron.deltaRule(net, expectedY, inputVector);
                }
            }
        }
    }

}
