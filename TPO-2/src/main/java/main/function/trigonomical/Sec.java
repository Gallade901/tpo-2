package main.function.trigonomical;

import main.function.BaseMathFunction;


public class Sec implements BaseMathFunction {
    private BaseMathFunction cos;

    public Sec() {
        cos = new Cosine();
    }

    public Sec(BaseMathFunction cos) {
        this.cos = cos;
    }

    @Override
    public double calculate(double x) {
        double cosValue = cos.calculate(x);
        if (Math.abs(cosValue) < 1e-5) {
            return Double.POSITIVE_INFINITY;
        }
        return 1 / cosValue;
    }
}
