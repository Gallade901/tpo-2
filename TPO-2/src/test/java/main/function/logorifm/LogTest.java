package main.function.logorifm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogTest {
    private static final double delta = 1e-5;

//    static Ln ln;
    static Log2 log2;
    static Log3 log3;
    static Log5 log5;
    static Log10 log10;

    @BeforeAll
    static void init() {
        Ln ln = mock(Ln.class);
        when(ln.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.log(x);
        });
        log2 = new Log2(ln);
        log3 = new Log3(ln);
        log5 = new Log5(ln);
        log10 = new Log10(ln);
    }


    @ParameterizedTest
    @CsvSource({
            "1, 0.0",
            "2, 1.0",
            "52, 5.70044",
            "0.5, -1.0"
    })
    void testLog2(double x, double y) {
        assertEquals(y, log2.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0.0",
            "2, 0.630929",
            "52, 3.596577",
            "0.5, -0.63092"
    })
    void testLog3(double x, double y) {
        assertEquals(y, log3.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0.0",
            "2, 0.43068",
            "52, 2.45504",
            "0.5, -0.43067"
    })
    void testLog5(double x, double y) {
        assertEquals(y, log5.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0.0",
            "2, 0.30103",
            "52, 1.71600",
            "0.5, -0.301029"
    })
    void testLog10(double x, double y) {
        assertEquals(y, log10.calculate(x), delta);
    }

//    @ParameterizedTest
//    @CsvSource({"-1", "0", "-52"})
//    void testInvalidInputs(double x) {
//        assertThrows(ArithmeticException.class, () -> log2.calculate(x));
//        assertThrows(ArithmeticException.class, () -> log3.calculate(x));
//        assertThrows(ArithmeticException.class, () -> log5.calculate(x));
//        assertThrows(ArithmeticException.class, () -> log10.calculate(x));
//    }

    @ParameterizedTest
    @CsvSource({"-1", "-52"})
    void testInvalidInputs(double x) {
        assertEquals(Double.NaN, log2.calculate(x));
        assertEquals(Double.NaN, log3.calculate(x));
        assertEquals(Double.NaN, log5.calculate(x));
        assertEquals(Double.NaN, log10.calculate(x));
    }

    @ParameterizedTest
    @CsvSource({"0"})
    void testExtrInputs(double x) {
        assertEquals(Double.NEGATIVE_INFINITY, log2.calculate(x));
        assertEquals(Double.NEGATIVE_INFINITY, log3.calculate(x));
        assertEquals(Double.NEGATIVE_INFINITY, log5.calculate(x));
        assertEquals(Double.NEGATIVE_INFINITY, log10.calculate(x));
    }
}
