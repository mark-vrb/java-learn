package org.markvarabyou.ui;

import org.markvarabyou.math.arraylistbased.Matrix;
import org.markvarabyou.math.common.Calculators.DoubleCalculator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    private static DoubleCalculator calculator = new DoubleCalculator();

    private static Double[] generateTestMatrixValues(int rowCount, int colCount){
        Double[] values = new Double[rowCount * colCount];
        Random random = new Random();
        for (int i = 0; i < rowCount * colCount; i++){
            values[i] = random.nextDouble();
        }
        return values;
    }

    private static long measureArrayListTime(int rows, int cols, Double[] first, Double[] second){
        long startTime = System.nanoTime();

        Matrix<Double> matrix1;
        Matrix<Double> matrix2;

        matrix1 = new Matrix<Double>(rows, cols, first, calculator);
        matrix2 = new Matrix<Double>(rows, cols, second, calculator);

        matrix1.multiply(matrix2);

        return System.nanoTime() - startTime;
    }

    private static long measureLinkedListTime(int rows, int cols, Double[] first, Double[] second){
        long startTime = System.nanoTime();

        org.markvarabyou.math.linkedlistbased.Matrix<Double> matrix1;
        org.markvarabyou.math.linkedlistbased.Matrix<Double> matrix2;

        matrix1 = new org.markvarabyou.math.linkedlistbased.Matrix<Double>(rows, cols, first, calculator);
        matrix2 = new org.markvarabyou.math.linkedlistbased.Matrix<Double>(rows, cols, second, calculator);

        matrix1.multiply(matrix2);

        return System.nanoTime() - startTime;
    }

    private static void compareListImplementations(){
        int rows = 100;
        int cols = 100;

        Double[] values1 = generateTestMatrixValues(rows, cols);
        Double[] values2 = generateTestMatrixValues(rows, cols);

        System.out.println(String.format("ArrayList based Matrix elapsed time: \t%d%n",
                measureArrayListTime(rows, cols, values1, values2)));

        System.out.println(String.format("LinkedList based Matrix elapsed time: \t%d%n",
                measureLinkedListTime(rows, cols, values1, values2)));
    }

    private static void serializationTest(String fileName){
        int rows = 100;
        int cols = 100;

        Double[] values1 = generateTestMatrixValues(rows, cols);

        // Serializing
        Matrix<Double> m = new Matrix<Double>(rows, cols, values1, new DoubleCalculator());
        m.serialize(fileName);

        // Deserializer
        m = new Matrix<Double>(new DoubleCalculator());
        m.deserialize(fileName);

        for (int i = 0; i < m.getRowCount(); i++){
            for (int j = 0; j < m.getColCount(); j++){
                System.out.print(String.format("\t%s", m.get(i, j)));
            }
            System.out.println();
        }
    }

    private static void readerWriterTest(String fileName) {
        int rows = 100;
        int cols = 100;

        Double[] values1 = generateTestMatrixValues(rows, cols);

        // Serializing
        Matrix<Double> m = new Matrix<Double>(rows, cols, values1, new DoubleCalculator());
        m.writeToFile(fileName);

        // Deserializer
        m = new Matrix<Double>(new DoubleCalculator());
        m.readFromFile(fileName);

        for (int i = 0; i < m.getRowCount(); i++){
            for (int j = 0; j < m.getColCount(); j++){
                System.out.print(String.format("\t%s", m.get(i, j)));
            }
            System.out.println();
        }
    }

    private static void symbolsUsageStatistics(String fileName) {
        SymbolsUsageStatistics statistics = new SymbolsUsageStatistics(fileName);
        statistics.processFile();
        HashMap<Character,Integer> symbolsUsage = statistics.getSymbolsUsage();
        System.out.println("Symbols usage statistics:");
        for (Map.Entry<Character, Integer> entry : symbolsUsage.entrySet())
        {
            System.out.println("'" + entry.getKey() + "' -> " + entry.getValue());
        }
    }

    public static void main(String[] args) throws IOException {
        // #2 Comparing matrix implementations
        //compareListImplementations();

        // #3.1 Statistics of symbols usage
        //symbolsUsageStatistics("D:\\file.txt");

        // #3.2 Reader/Writer for saving/restoring Matrix
        //readerWriterTest("D:\\file1.txt");

        // #3.3 Serialization for Matrix
        serializationTest("D:\\file2");
    }
}
