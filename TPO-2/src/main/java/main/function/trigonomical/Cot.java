package main.function.trigonomical;

import main.function.BaseMathFunction;

public class Cot implements BaseMathFunction {
    private BaseMathFunction cos;
    private BaseMathFunction sin;

    public Cot() {
        cos = new Cosine();
        sin = new Sinus();
    }

    public Cot(BaseMathFunction cos, BaseMathFunction sin) {
        this.cos = cos;
        this.sin = sin;
    }

    public Cot(BaseMathFunction sin) {
        this.sin = sin;
        cos = new Cosine();
    }

    @Override
    public double calculate(double x) {
        double sinValue = sin.calculate(x);
        double cosValue = cos.calculate(x);

        if (Math.abs(sinValue) < 1e-5) {
            return Double.POSITIVE_INFINITY;
        }
        return cosValue / sinValue;
    }
}
