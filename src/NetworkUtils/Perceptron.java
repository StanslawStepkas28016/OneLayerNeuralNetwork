package NetworkUtils;

import java.util.*;

public class Perceptron {
    private final Integer dataVectorSize;
    public final Double learnRate;
    public Double tVal;
    private String language;
    public double[] weights;

    public Perceptron(Integer dataVectorSize, Double learnRate) {
        this.dataVectorSize = dataVectorSize;
        this.learnRate = learnRate;
        // this.tVal = 1.0; // Defaultowo 1, zmieniane, przy okazji deltaRule.

        weights = new double[dataVectorSize];
        Random random = new Random();

        // Inicjalizacja wektora wag, wagami z zakresu <-5,5>.
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble(-0.1, 0.1);
//            weights[i] = random.nextDouble(-5, 5);
        }

    }

    /* Metoda służy do zastosowania reguły DELTA. */
    public void deltaRule(Integer receivedValue, Integer expectedValue, double[] inputVector) {
        double[] oldWeights = weights;
        double[] newWeights = new double[dataVectorSize];

        // Prawa część wzoru W’ = W + (d-y) * 𝛼 * X, konkretnie (d-y) * 𝛼 * X.
        for (int i = 0; i < inputVector.length; i++) {
            inputVector[i] = (expectedValue - receivedValue) * learnRate * inputVector[i];
        }
        // Suma starego wektora oldWeights z przerobionym (po prawej części wzoru) inputVector.
        for (int i = 0; i < newWeights.length; i++) {
            newWeights[i] = inputVector[i] + oldWeights[i];
        }

        // Wyliczenie nowego t (t’= t - (d-y) * 𝛼).
        // tVal = tVal - (expectedValue - receivedValue) * learnRate;

        // Inicjalizacja pola weights nowym wektorem wag.
        weights = newWeights;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return STR."Perceptron{weights=\{Arrays.toString(weights)}\{'}'}";
    }
}
