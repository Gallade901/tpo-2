package main.function.trigonomical;

import main.function.BaseMathFunction;

import static java.lang.Math.PI;

public class Cosine implements BaseMathFunction {
    private BaseMathFunction sin;
    public Cosine() {
        sin = new Sinus();
    }

    public Cosine(BaseMathFunction sin) {
        this.sin = sin;
    }

    @Override
    public double calculate(double x) {
        double y = sin.calculate(x + PI / 2);
        if (Math.abs(y) < 1e-4) return 0;
        return y;
    }
}
