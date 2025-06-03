package main;


import main.function.BaseMathFunction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvWriter {
    public static void writeToCsv(BaseMathFunction function,
                                  Double start,
                                  Double end,
                                  Double step) throws IOException {

        if (start >= end) {
            throw new IllegalArgumentException("Начало интервала должно быть меньше конца");
        }
        if (step <= 0) {
            throw new IllegalArgumentException("Шаг должен быть положительным");
        }

        Path csvDir = Paths.get("csv");
        if (!Files.exists(csvDir)) {
            Files.createDirectory(csvDir);
        }

        String filename = String.format("%s_%s_%s_%s.csv",
                function.getClass().getSimpleName().toLowerCase(),
                start.toString(),
                end.toString(),
                step.toString());

        Path filePath = csvDir.resolve(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("x,f(x)\n");

            Double current = start;
            String value;
            while (current <= end) {
                try {
                    value = Double.toString(function.calculate(current));
                }
                catch (ArithmeticException e) {
                    value = "undefined";
                }
                writer.write(String.format("%s,%s\n", current, value ));
                current = current += step;
            }
        }
    }
}