package org.markvarabyou.ui;

import org.markvarabyou.math.arraylistbased.Matrix;
import org.markvarabyou.math.common.Calculators.DoubleCalculator;

import java.io.*;
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

    public static void main(String[] args) throws IOException {
        int rows = 100;
        int cols = 100;

        Double[] values1 = generateTestMatrixValues(rows, cols);
//        Double[] values2 = generateTestMatrixValues(rows, cols);
//
//        System.out.println(String.format("ArrayList based Matrix elapsed time: \t%d%n",
//                measureArrayListTime(rows, cols, values1, values2)));
//
//        System.out.println(String.format("LinkedList based Matrix elapsed time: \t%d%n",
//                measureLinkedListTime(rows, cols, values1, values2)));

        // Serializing
//        Matrix<Double> m = new Matrix<Double>(2, 2, new Double[]{1.0, 2.0, 3.0, 4.0}, new DoubleCalculator());
        Matrix<Double> m = new Matrix<Double>(rows, cols, values1, new DoubleCalculator());
        //m.serialize("/tmp/matrix.ser");
        m.writeToFile("/tmp/matrix.txt");

        // Deserializer
        m = new Matrix<Double>(new DoubleCalculator());
        m.readFromFile("/tmp/matrix.txt");
        //m.deserialize("/tmp/matrix.ser");

        for (int i = 0; i < m.getRowCount(); i++){
            for (int j = 0; j < m.getColCount(); j++){
                System.out.print(String.format("\t%s", m.get(i, j)));
            }
            System.out.println();
        }

    }
}
