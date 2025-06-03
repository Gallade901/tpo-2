package main;

import main.function.logorifm.*;
import main.function.trigonomical.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.PI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Sinus sin = new Sinus();
        Cosine cos = new Cosine();
        Tan tan = new Tan();
        Cot cot = new Cot();
        Csc csc = new Csc();
        Sec sec = new Sec();
        Log3 log3 = new Log3();
        Log2 log2 = new Log2();
        Log10 log10 = new Log10();
        Log5 log5 = new Log5();
        Ln ln = new Ln();

        double start = -2 * PI - 0.25;
        double end = 2 * PI + 0.25;

        String prefix = "C:\\Users\\MSI\\Desktop\\reports\\ТПО\\lab-2\\TPO-2\\src\\main\\resources\\";
        String postfix = ".csv";
        List<String> paths = List.of("sin", "cos");

        try {
            BufferedReader br = new BufferedReader(new FileReader(prefix + paths.get(0) + postfix));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        try {
//            CsvWriter.writeToCsv(sin, start, end, 0.1);
//            CsvWriter.writeToCsv(cos, start, end, 0.1);
//            CsvWriter.writeToCsv(tan, start, end, 0.1);
//            CsvWriter.writeToCsv(cot, start, end, 0.1);
//            CsvWriter.writeToCsv(csc, start, end, 0.1);
//            CsvWriter.writeToCsv(sec, start, end, 0.1);
//            CsvWriter.writeToCsv(log3, start, end, 0.1);
//            CsvWriter.writeToCsv(log2, start, end, 0.1);
//            CsvWriter.writeToCsv(log10, start, end, 0.1);
//            CsvWriter.writeToCsv(log5, start, end, 0.1);
//            CsvWriter.writeToCsv(ln, start, end, 0.1);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}