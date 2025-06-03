package main.function.trigonomical;

import main.function.BaseMathFunction;

public class Tan implements BaseMathFunction {
    private BaseMathFunction cos;
    private BaseMathFunction sin;

    public Tan() {
        cos = new Cosine();
        sin = new Sinus();
    }

    public Tan(BaseMathFunction cos, BaseMathFunction sin) {
        this.cos = cos;
        this.sin = sin;
    }

    public Tan(BaseMathFunction sin) {
        cos = new Cosine();
        this.sin = sin;
    }

    @Override
    public double calculate(double x) {
        double cosValue = cos.calculate(x);
        double sinValue = sin.calculate(x);

        if (Math.abs(cosValue) < 1e-5) {
            return Double.POSITIVE_INFINITY;
        }
        return sinValue / cosValue;
    }
}
