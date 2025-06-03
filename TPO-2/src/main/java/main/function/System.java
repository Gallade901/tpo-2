package main.function;

import main.function.logorifm.*;
import main.function.trigonomical.*;

import static java.lang.Math.pow;

public class System implements BaseMathFunction {
    private BaseMathFunction cos;
    private BaseMathFunction sin;
    private BaseMathFunction tan;
    private BaseMathFunction cot;
    private BaseMathFunction csc;
    private BaseMathFunction sec;
    private BaseMathFunction log10;
    private BaseMathFunction log5;
    private BaseMathFunction log3;
    private BaseMathFunction log2;
    private BaseMathFunction ln;

    public System() {
        cos = new Cosine();
        sin = new Sinus();
        tan = new Tan();
        cot = new Cot();
        csc = new Csc();
        sec = new Sec();
        log3 = new Log3();
        log2 = new Log2();
        log10 = new Log10();
        log5 = new Log5();
        ln = new Ln();
    }

    public System(BaseMathFunction cos, BaseMathFunction sin, BaseMathFunction tan, BaseMathFunction cot, BaseMathFunction csc, BaseMathFunction sec, BaseMathFunction log3, BaseMathFunction log2, BaseMathFunction log5, BaseMathFunction log10, BaseMathFunction ln) {
        this.cos = cos;
        this.sin = sin;
        this.tan = tan;
        this.cot = cot;
        this.csc = csc;
        this.sec = sec;
        this.log3 = log3;
        this.log2 = log2;
        this.log10 = log10;
        this.log5 = new Log5();
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        double res;
        if (x <= 0) {
            res = (pow((((((pow((((((sin.calculate(x) * tan.calculate(x)) - tan.calculate(x)) / csc.calculate(x)) * sin.calculate(x)) / csc.calculate(x)), 3) / sec.calculate(x)) + cot.calculate(x)) - (sec.calculate(x) * ((((cos.calculate(x) * (cot.calculate(x) * tan.calculate(x))) + cot.calculate(x)) / cos.calculate(x)) * cos.calculate(x)))) + cos.calculate(x)) * (cos.calculate(x) * (pow(cos.calculate(x), 2) / cot.calculate(x)))), 2) - pow((((pow(((cos.calculate(x) / sin.calculate(x)) - pow(cot.calculate(x), 3)), 2) + sec.calculate(x)) - ((sin.calculate(x) - tan.calculate(x)) / (cos.calculate(x) - (sec.calculate(x) - sin.calculate(x))))) / ((cot.calculate(x) * (csc.calculate(x) / pow(cos.calculate(x), 3))) * csc.calculate(x))), 2));
        } else {
            res = (((((log10.calculate(x) / log5.calculate(x)) - (log3.calculate(x) + log5.calculate(x))) + ln.calculate(x)) - (log10.calculate(x) - log2.calculate(x))) / (log5.calculate(x) / (log3.calculate(x) - log5.calculate(x))));
        }
        if (Math.abs(res) < 1e-3) return 0;
        return res;
    }
}
