package main.function.trigonomical;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinusTest {
    private static final double delta = 1e-5;
    static Sinus sin;

    @BeforeAll
    static void init(){
        sin = new Sinus();
    }

//    static Stream<Double> provideSinTest() {
//        Stream.Builder<Double> builder = Stream.builder();
//        // мапа
//        for (int i = 0; i < 8; i++) {
//            builder.add(PI * i / 4);
//        }
//        return builder.build();
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sin.csv", numLinesToSkip = 0)
    void testSin(double x, double y) {
        assertEquals(y, sin.calculate(x), delta);
    }

}
