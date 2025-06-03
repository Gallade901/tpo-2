package main.function;

public interface BaseMathFunction {
    default double getEpsilon(){
        return 1e-150;
    }
    double calculate(double x);
}
