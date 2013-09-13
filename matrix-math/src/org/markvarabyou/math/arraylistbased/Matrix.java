package org.markvarabyou.math.arraylistbased;

import org.markvarabyou.math.common.Calculator;
import java.util.ArrayList;

/**
 * Abstract generic class for math matrix.
 * Author: Mark Vorobyov
 * Date: 9/9/13
 * Time: 3:44 PM
 */
public class Matrix<T> {

    private ArrayList<T> elements;
    private int rowCount;
    private int colCount;
    private Calculator<T> calculator;

    /**
     * Initializes new matrix rowCount x colCount with values from array
     * @param rowCount Number of rows in new matrix
     * @param colCount Number of columns in new matrix
     * @param array Array with values to initialize matrix
     */
    public Matrix(int rowCount, int colCount, T[] array, Calculator<T> calculator) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.calculator = calculator;
        if (!isInRange(rowCount*colCount, 0, array.length))
            throw new IllegalArgumentException("Invalid array length");
        elements = new ArrayList<T>();
        for (T element : array){
            elements.add(element);
        }
    }

    public Matrix(int rowCount, int colCount, Calculator<T> calculator) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.calculator = calculator;
        elements = new ArrayList<T>();
        for (int i = 0; i < rowCount*colCount; i++){
            elements.add(calculator.getNew());
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public T get(int row, int col){
        checkRowAndColCount(row, col, rowCount, colCount);
        return elements.get(row * colCount + col);
    }

    public T set(int row, int col, T value){
        checkRowAndColCount(row, col, rowCount, colCount);
        return elements.set(row * colCount + col, value);
    }

    public Vector<T> getRow(int row){
        //TODO: Add validation here
        ArrayList<T> vector = new ArrayList<T>();
        int startIndex = this.getColCount() * row;
        for (int i = startIndex; i < startIndex + this.getColCount(); i++){
            vector.add(elements.get(i));
        }
        return new Vector<T>(vector, calculator);
    }

    public void setRow(int row, Vector<T> vector){
        //TODO: add implementation
    }

    public Vector<T> getCol(int col){
        //TODO: Add validation here
        ArrayList<T> vector = new ArrayList<T>();
        for (int i = col; i < colCount * rowCount; i+=colCount){
            vector.add(elements.get(i));
        }
        return new Vector<T>(vector, calculator);
    }

    public void setCol(int col, Vector<T> vector){
        //TODO: add implementation
    }

    public Matrix<T> add(Matrix<T> matrix){
        Matrix<T> result = new Matrix<T>(rowCount, colCount, calculator);

        // Validation
        if (this.rowCount != matrix.getRowCount() || this.colCount != matrix.getColCount()){
            throw new IllegalArgumentException("Illegal size of matrix to add");
        }

        for (int i = 0; i < getRowCount(); i++){
            for (int j = 0; j < getColCount(); j++)
                this.set(i, j, calculator.sum(this.get(i, j), matrix.get(i, j)));
        }

        return this;
    }

    public Matrix<T> multiply(T value){
        for (int i = 0; i < getRowCount(); i++){
            for (int j = 0; j < getColCount(); j++)
                this.set(i, j, calculator.mul(this.get(i, j), value));
        }
        return this;
    }

    public Matrix<T> multiply(Matrix<T> matrix){
        Matrix<T> result =  new Matrix<T>(rowCount, colCount, calculator);

        // Validation
        if (this.getColCount() != matrix.getRowCount())
            throw new IllegalArgumentException("Matrices are not consistent");

        for (int i = 0; i < this.getRowCount(); i++){
            for (int j = 0; j < matrix.getColCount(); j++){

                //TODO: add implementation
            }
        }
        return result;
    }

    private boolean isInRange(int value, int left, int right){
        return value >= left && value <= right;
    }

    private void checkRowAndColCount(int row, int col, int rowCount, int colCount) {
        if (!isInRange(row, 0, rowCount) || !isInRange(col, 0, colCount))
            throw new IllegalArgumentException("Invalid row or column index");
    }
}
