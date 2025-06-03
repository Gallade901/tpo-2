package main.function;

import main.function.logorifm.*;
import main.function.trigonomical.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemTest {
    private static final double delta = 1e-4;
    static Sinus sinMock;
    static Cosine cosMock;
    static Sinus sin;
    static Cosine cos;
    static Tan tan;
    static Csc csc;
    static Sec sec;
    static Cot cot;
    static Ln ln;
    static Log2 log2;
    static Log3 log3;
    static Log5 log5;
    static Log10 log10;

    private static final ArrayList<Map> maps = new ArrayList<>();
    private static final Map<Double, Double> sinMap = new HashMap<>();
    private static final Map<Double, Double> cosMap = new HashMap<>();
    private static final String prefix = "C:\\Users\\MSI\\Desktop\\reports\\ТПО\\lab-2\\TPO-2\\src\\main\\resources\\";
    private static final String postfix = ".csv";
    private static List<String> paths = List.of("sysSin", "sysCos");

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
        sin = mock(Sinus.class);
        when(sin.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            double closestKey = sinMap.keySet().stream()
                    .min((k1, k2) -> Double.compare(Math.abs(k1 - x), Math.abs(k2 - x)))
                    .orElseThrow();
            return sinMap.get(closestKey);
        });
        cos = mock(Cosine.class);
        when(cos.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            double closestKey = cosMap.keySet().stream()
                    .min((k1, k2) -> Double.compare(Math.abs(k1 - x), Math.abs(k2 - x)))
                    .orElseThrow();
            return cosMap.get(closestKey);
        });
//        sin = mock(Sinus.class);
//        when(sin.calculate(anyDouble())).thenAnswer(invocation -> {
//            double x = invocation.getArgument(0);
//            return Math.sin(x);
//        });
//        cos = mock(Cosine.class);
//        when(cos.calculate(anyDouble())).thenAnswer(invocation -> {
//            double x = invocation.getArgument(0);
//            return Math.cos(x);
//        });
        tan = mock(Tan.class);
        when(tan.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.tan(x);
        });
        csc = mock(Csc.class);
        when(csc.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return 1 / Math.sin(x);
        });
        sec = mock(Sec.class);
        when(sec.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return 1 / Math.cos(x);
        });
        cot = mock(Cot.class);
        when(cot.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.cos(x) / Math.sin(x);
        });
        ln = mock(Ln.class);
        log2 = mock(Log2.class);
        log3 = mock(Log3.class);
        log5 = mock(Log5.class);
        log10 = mock(Log10.class);
        when(ln.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.log(x);
        });
        when(log10.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.log10(x);
        });
        when(log2.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.log(x) / Math.log(2);
        });
        when(log3.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.log(x) / Math.log(3);
        });
        when(log5.calculate(anyDouble())).thenAnswer(invocation -> {
            double x = invocation.getArgument(0);
            return Math.log(x) / Math.log(5);
        });

    }

    public static double resultSystem(double x) {
        double res;
        if (x <= 0) {
            res = (pow((((((pow((((((sin.calculate(x) * tan.calculate(x)) - tan.calculate(x)) / csc.calculate(x)) * sin.calculate(x)) / csc.calculate(x)), 3) / sec.calculate(x)) + cot.calculate(x)) - (sec.calculate(x) * ((((cos.calculate(x) * (cot.calculate(x) * tan.calculate(x))) + cot.calculate(x)) / cos.calculate(x)) * cos.calculate(x)))) + cos.calculate(x)) * (cos.calculate(x) * (pow(cos.calculate(x), 2) / cot.calculate(x)))), 2) - pow((((pow(((cos.calculate(x) / sin.calculate(x)) - pow(cot.calculate(x), 3)), 2) + sec.calculate(x)) - ((sin.calculate(x) - tan.calculate(x)) / (cos.calculate(x) - (sec.calculate(x) - sin.calculate(x))))) / ((cot.calculate(x) * (csc.calculate(x) / pow(cos.calculate(x), 3))) * csc.calculate(x))), 2));
        } else {
            res = (((((log10.calculate(x) / log5.calculate(x)) - (log3.calculate(x) + log5.calculate(x))) + ln.calculate(x)) - (log10.calculate(x) - log2.calculate(x))) / (log5.calculate(x) / (log3.calculate(x) - log5.calculate(x))));
        }
        return res;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/system.csv")
    void testSystemReal(double x, double y) {
        main.function.System system = new main.function.System();
        assertEquals(y, system.calculate(x), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/system.csv")
    void testSystemMock(double x, double y) {
        assertEquals(y, resultSystem(x), delta);
    }

}
