package main.function.logorifm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {
    private static final double delta = 1e-5;
    static Ln ln;

    @BeforeAll
    static void init(){
        ln = new Ln();
    }

    @ParameterizedTest()
    @CsvSource({
            "1.0, 0.0",
            "2.7182818285, 1.0",
            "5.0, 1.60944",
            "52.0, 3.95124"
    })
    void lnValid(double x, double y) {
        double result = ln.calculate(x);
        assertEquals(y, result, delta);
    }

    @ParameterizedTest(name = "ln({0}) should throw ArithmeticException")
    @CsvSource({
            "0.0",
            "-1.0",
            "-10.0",
            "-1e-11"
    })
    void testBaseELogarithmUndefined(double input) {
        assertThrows(ArithmeticException.class, () -> ln.calculate(input));
    }

}
