package se.ifmo.ru.lab1.task1;

public class CosSeries {

    public double calculate(double x, double epsilon) {
        if (epsilon <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be positive");
        }

        double term = 1.0;
        double sum = term;
        int k = 1;

        while (Math.abs(term) >= epsilon) {
            term *= -x * x / ((2L * k - 1L) * (2L * k));
            sum += term;
            k++;

            if (k > 1_000_000) {
                throw new IllegalStateException("Series did not converge");
            }
        }

        return sum;
    }
}

