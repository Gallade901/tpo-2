package main.function.logorifm;

import main.function.trigonomical.Sinus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogTest {
    private static final double delta = 1e-5;

    static Ln ln;
    static Log2 log2;
    static Log3 log3;
    static Log5 log5;
    static Log10 log10;

//    @BeforeAll
//    static void init() {
//        Ln ln = mock(Ln.class);
//        when(ln.calculate(anyDouble())).thenAnswer(invocation -> {
//            double x = invocation.getArgument(0);
//            return Math.log(x);
//        });
//        log2 = new Log2(ln);
//        log3 = new Log3(ln);
//        log5 = new Log5(ln);
//        log10 = new Log10(ln);
//    }

    private static final Map<Double, Double> lnMap = new HashMap<>();
    private static final String prefix = "C:\\Users\\MSI\\Desktop\\reports\\ТПО\\lab-2\\TPO-2\\src\\main\\resources\\";
    private static final String postfix = ".csv";
    private static List<String> paths = List.of("ln");
    private static final ArrayList<Map> maps = new ArrayList<>();


    @BeforeAll
    static void setMap() {
        maps.add(lnMap);
        for (int i = 0; i < maps.size(); i++) {
            String path = prefix + paths.get(i) + postfix;
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.trim().split(",");
                    double x = Double.parseDouble(parts[0]);
                    double fx = Double.parseDouble(parts[1]);
                    maps.get(i).put(x, fx);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BeforeEach
    void initLn() {
        ln = mock(Ln.class);
        when(ln.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            // Округляем
            double closestKey = lnMap.keySet().stream()
                    .min((k1, k2) -> Double.compare(Math.abs(k1 - x), Math.abs(k2 - x)))
                    .orElseThrow();
            return lnMap.get(closestKey);
        });
        log2 = new Log2();
        log3 = new Log3();
        log5 = new Log5();
        log10 = new Log10();
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
            "2, 1.0",
            "52, 5.70044",
            "0.5, -1.0"
    })
    void testLog2Mock(double x, double y) {
        log2 = new Log2(ln);
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
            "2, 0.630929",
            "52, 3.596577",
            "0.5, -0.63092"
    })
    void testLog3Mock(double x, double y) {
        log3 = new Log3(ln);
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
            "2, 0.43068",
            "52, 2.45504",
            "0.5, -0.43067"
    })
    void testLog5Mock(double x, double y) {
        log5 = new Log5(ln);
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

    @ParameterizedTest
    @CsvSource({"-1", "0", "-52"})
    void testInvalidInputs(double x) {
        assertThrows(ArithmeticException.class, () -> log2.calculate(x));
        assertThrows(ArithmeticException.class, () -> log3.calculate(x));
        assertThrows(ArithmeticException.class, () -> log5.calculate(x));
        assertThrows(ArithmeticException.class, () -> log10.calculate(x));
    }


}
