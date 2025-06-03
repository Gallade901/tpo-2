package main.function.logorifm;

import main.function.BaseMathFunction;

public class Log2 implements BaseMathFunction {
    private BaseMathFunction ln;
    public Log2() {
        ln = new Ln();
    }

    public Log2(BaseMathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(2);
    }
}
