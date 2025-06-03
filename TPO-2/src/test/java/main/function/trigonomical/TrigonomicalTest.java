package main.function.trigonomical;

import main.function.logorifm.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrigonomicalTest {
    private static final double delta = 1e-4;
    static Sinus sinMock;
    static Cosine cosMock;
    private static final ArrayList<Map> maps = new ArrayList<>();
    private static final Map<Double, Double> sinMap = new HashMap<>();
    private static final Map<Double, Double> cosMap = new HashMap<>();
    private static final String prefix = "C:\\Users\\MSI\\Desktop\\reports\\ТПО\\lab-2\\TPO-2\\src\\main\\resources\\";
    private static final String postfix = ".csv";
    private static List<String> paths = List.of("sin", "cos");


    @BeforeAll
    static void setMap() {
        maps.add(sinMap);
        maps.add(cosMap);
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

    @BeforeAll
    static void init() {
        sinMock = mock(Sinus.class);
        when(sinMock.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            // Округляем
            double closestKey = sinMap.keySet().stream()
                    .min((k1, k2) -> Double.compare(Math.abs(k1 - x), Math.abs(k2 - x)))
                    .orElseThrow();
            return sinMap.get(closestKey);
        });
        cosMock = mock(Cosine.class);
        when(cosMock.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            double closestKey = cosMap.keySet().stream()
                    .min((k1, k2) -> Double.compare(Math.abs(k1 - x), Math.abs(k2 - x)))
                    .orElseThrow();
            return cosMap.get(closestKey);
        });
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/cos.csv")
    void testCosRealSinus(double x, double y) {
        Cosine cosMy = new Cosine();
        assertEquals(y, cosMy.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cos.csv")
    void testCosMockSinus(double x, double y) {
        Cosine cos = new Cosine(sinMock);
        assertEquals(y, cos.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csc.csv")
    void testCscRealSinus(double x, double y) {
        Csc cscMy = new Csc();
        assertEquals(y, cscMy.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csc.csv")
    void testCscMockSinus(double x, double y) {
        Csc csc = new Csc(sinMock);
        assertEquals(y, csc.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sec.csv")
    void testSecRealCos(double x, double y) {
        Sec secMy = new Sec();
        assertEquals(y, secMy.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sec.csv")
    void testSecMockCos(double x, double y) {
        Sec sec = new Sec(cosMock);
        assertEquals(y, sec.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cot.csv")
    void testCotRealCosSin(double x, double y) {
        Cot cot = new Cot();
        assertEquals(y, cot.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cot.csv")
    void testCotMockSin(double x, double y) {
        Cot cot = new Cot(sinMock);
        assertEquals(y, cot.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cot.csv")
    void testCotMockSinCos(double x, double y) {
        Cot cot = new Cot(cosMock ,sinMock);
        assertEquals(y, cot.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tan.csv")
    void testTanRealCosSin(double x, double y) {
        Tan tan = new Tan();
        assertEquals(y, tan.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tan.csv")
    void testTanMockSin(double x, double y) {
        Tan tan = new Tan(sinMock);
        assertEquals(y, tan.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tan.csv")
    void testTanMockSinCos(double x, double y) {
        Tan tan = new Tan(cosMock, sinMock);
        assertEquals(y, tan.calculate(x), delta);
    }


}
