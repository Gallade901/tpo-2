package main.function.logorifm;

import main.function.BaseMathFunction;

public class Log10 implements BaseMathFunction {
    private BaseMathFunction ln;
    public Log10() {
        ln = new Ln();
    }

    public Log10(BaseMathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(10);
    }
}
