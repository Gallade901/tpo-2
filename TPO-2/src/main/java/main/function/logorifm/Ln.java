package main.function.logorifm;


import main.function.BaseMathFunction;

public class Ln implements BaseMathFunction {

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            throw new ArithmeticException();
        }
        double num, mul, cal, sum = 0;
        num = (x - 1) / (x + 1);

        cal = 2 * getEpsilon();
        int i = 0;
        while (Math.abs(cal) > getEpsilon())
        {
            i += 1;
            mul = (2 * i) - 1;
            cal = Math.pow(num, mul);
            cal = cal / mul;
            sum = sum + cal;
        }
        sum = 2 * sum;
        System.out.println(i);
        return sum;
    }
}
