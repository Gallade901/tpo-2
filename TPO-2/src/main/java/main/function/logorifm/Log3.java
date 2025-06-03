package main.function.logorifm;

import main.function.BaseMathFunction;


public class Log3 implements BaseMathFunction {
    private BaseMathFunction ln;
    public Log3() {
        ln = new Ln();
    }

    public Log3(BaseMathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(3);
    }
}
