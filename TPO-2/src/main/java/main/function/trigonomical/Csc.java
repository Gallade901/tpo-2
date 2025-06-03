package main.function.trigonomical;

import main.function.BaseMathFunction;

public class Csc implements BaseMathFunction {
    private BaseMathFunction sin;

    public Csc() {
        sin = new Sinus();
    }

    public Csc(BaseMathFunction sin) {
        this.sin = sin;
    }

    @Override
    public double calculate(double x) {
        double sinValue = sin.calculate(x);
        if (Math.abs(sinValue) < 1e-5) {
            return Double.POSITIVE_INFINITY;
        }
        return 1 / sinValue;
    }
}
