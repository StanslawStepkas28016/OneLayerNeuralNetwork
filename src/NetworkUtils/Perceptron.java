package NetworkUtils;

import java.util.*;

public class Perceptron {
    private final Integer dataVectorSize;
    public final Double learnRate;
    public Double tVal;
    private Integer hitZero = 0;
    private Integer hitOne = 0;
    private Integer totalZeroDataSize = 0;
    private Integer totalOneDataSize = 0;
    private Integer totalDataSize = 0;
    public double[] weights;

    public Perceptron(Integer dataVectorSize, Double learnRate) {
        this.dataVectorSize = dataVectorSize;
        this.learnRate = learnRate;
        this.tVal = 1.0; // Defaultowo 1, zmieniane, przy okazji deltaRule.

        weights = new double[dataVectorSize];
        Random random = new Random();

        // Inicjalizacja wektora wag, wagami z zakresu <-5,5>.
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble(-5, 5);
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
        tVal = tVal - (expectedValue - receivedValue) * learnRate;

        // Inicjalizacja pola weights nowym wektorem wag.
        weights = newWeights;
    }

    /* Metoda wyświetla dokładności klasyfikacji, resetując dane do następnych potoków.*/
    public void displayAccuracy() {
        System.out.println(STR."Dokładność (dla obu klas) : \{(hitOne + hitZero) / (double) totalDataSize}.");
        System.out.println(STR."Dokładność  (dla klasy 0) : \{hitZero / (double) totalZeroDataSize}.");
        System.out.println(STR."Dokładność  (dla klasy 1) : \{hitOne / (double) totalOneDataSize}.");
        System.out.println(STR."Aktualny parametr learnRate : \{learnRate}.");

        // Reset danych, do kolejnych obliczeń, przy zapętleniu działania programu.
        hitZero = 0;
        hitOne = 0;
        totalDataSize = 0;
        totalZeroDataSize = 0;
        totalOneDataSize = 0;
    }
}
