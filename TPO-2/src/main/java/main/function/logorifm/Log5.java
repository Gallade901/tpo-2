package main.function.logorifm;

import main.function.BaseMathFunction;

public class Log5 implements BaseMathFunction {
    private BaseMathFunction ln;
    public Log5() {
        ln = new Ln();
    }

    public Log5(BaseMathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(5);
    }
}
