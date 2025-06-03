package main.function.trigonomical;

import main.function.BaseMathFunction;

public class Sinus implements BaseMathFunction {

    @Override
    public double calculate(double x) {
        double sum = 0.0;
        double term = x;
        int sign = 1;
        int n = 0;
        double epsilon = getEpsilon();
        while (Math.abs(term) >= epsilon) {
            sum += sign * term;
            term *= x * x / ((2 * n + 2) * (2 * n + 3));  // n + 1 n + 2
            sign = -sign;
            n++;
        }
        return sum;
    }
}
