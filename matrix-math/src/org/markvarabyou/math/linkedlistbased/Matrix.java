package org.markvarabyou.math.linkedlistbased;

import org.markvarabyou.math.common.Calculator;
import org.markvarabyou.math.common.MatrixExceptionHelper;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Class for matrix math based on LinkedList.
 * Author: Mark Vorobyov
 * Date: 9/9/13
 * Time: 3:44 PM
 */
public class Matrix<T> {

    private LinkedList<T> elements;
    private int rowCount;
    private int colCount;
    private Calculator<T> calculator;
    private MatrixExceptionHelper helper = new MatrixExceptionHelper();

    /**
     * Initializes new matrix rowCount x colCount with values from array and calculator
     * @param rowCount Number of rows in new matrix
     * @param colCount Number of columns in new matrix
     * @param array Array with values to initialize matrix
     * @param calculator Object-calculator for matrix elements
     */
    public Matrix(int rowCount, int colCount, T[] array, Calculator<T> calculator) {
        helper.checkLength(array.length, rowCount * colCount, "array");

        this.rowCount = rowCount;
        this.colCount = colCount;
        this.calculator = calculator;
        elements = new LinkedList<T>();
        Collections.addAll(elements, array);
    }

    /**
     * Initializes new matrix rowCount x colCount with initial values and calculator
     * @param rowCount Number of rows in new matrix
     * @param colCount Number of columns in new matrix
     * @param calculator Object-calculator for matrix elements
     */
    public Matrix(int rowCount, int colCount, Calculator<T> calculator) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.calculator = calculator;
        elements = new LinkedList<T>();
        for (int i = 0; i < rowCount*colCount; i++){
            elements.add(calculator.getNew());
        }
    }

    /**
     * @return Count of rows in matrix
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @return Count of columns in matrix
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * @param row number of element row
     * @param col number of element column
     * @return value of element of matrix
     */
    public T get(int row, int col){
        helper.checkRowAndColCount(row, col, rowCount, colCount);

        return elements.get(row * colCount + col);
    }

    /**
     * Sets the value to element in specified row and column
     * @param row number of element row
     * @param col number of element column
     * @param value value of element to set
     * @return set value
     */
    public T set(int row, int col, T value){
        helper.checkRowAndColCount(row, col, rowCount, colCount);

        return elements.set(row * colCount + col, value);
    }

    /**
     * Convert specified row into vector an returns it
     * @param row number of row          ArrayList
     * @return vector from specified matrix row
     */
    public Vector<T> getRow(int row){
        helper.checkRange(row, rowCount);

        LinkedList<T> vector = new LinkedList<T>();
        int startIndex = this.getColCount() * row;
        for (int i = startIndex; i < startIndex + this.getColCount(); i++){
            vector.add(elements.get(i));
        }
        return new Vector<T>(vector, calculator);
    }

    /**
     * Sets the vector values to elements from specified row
     * @param row number of row
     * @param vector vector with new values for row
     */
    public void setRow(int row, Vector<T> vector){
        helper.checkRange(row, rowCount);
        helper.checkLength(vector.getLength(), colCount, "vector");

        for (int i = 0; i < colCount; i++){
            set(row, i, vector.get(i));
        }
    }

    /**
     * Convert specified column into vector an returns it
     * @param col number of column
     * @return vector from specified matrix column
     */
    public Vector<T> getCol(int col){
        helper.checkRange(col, colCount);

        LinkedList<T> vector = new LinkedList<T>();
        for (int i = col; i < colCount * rowCount; i+=colCount){
            vector.add(elements.get(i));
        }
        return new Vector<T>(vector, calculator);
    }

    /**
     * Sets the vector values to elements from specified column
     * @param col number of column
     * @param vector vector with new values for column
     */
    public void setCol(int col, Vector<T> vector){
        helper.checkRange(col, colCount);
        helper.checkLength(vector.getLength(), rowCount, "vector");

        for (int i = 0; i < rowCount; i++){
            set(i, col, vector.get(i));
        }
    }

    /**
     * Adds specified matrix with this (only if sizes is equal)
     * @param matrix matrix to add
     * @return result of adding
     */
    public Matrix<T> add(Matrix<T> matrix){
        Matrix<T> result = new Matrix<T>(rowCount, colCount, calculator);

        // Validation
        if (this.rowCount != matrix.getRowCount() || this.colCount != matrix.getColCount()){
            throw new IllegalArgumentException("Illegal size of matrix to add");
        }

        for (int i = 0; i < getRowCount(); i++){
            for (int j = 0; j < getColCount(); j++)
                result.set(i, j, calculator.sum(this.get(i, j), matrix.get(i, j)));
        }

        return result;
    }

    /**
     * Multiplies current matrix to single value
     * @param value value to be multiplied
     * @return result matrix
     */
    public Matrix<T> multiply(T value){
        Matrix<T> result = new Matrix<T>(rowCount, colCount, calculator);

        for (int i = 0; i < getRowCount(); i++){
            for (int j = 0; j < getColCount(); j++)
                result.set(i, j, calculator.mul(this.get(i, j), value));
        }
        return result;
    }

    /**
     * Multiplies current matrix to specified (only if matrices is consistent)
     * @param matrix matrix to multiply by
     * @return result matrix
     */
    public Matrix<T> multiply(Matrix<T> matrix){
        Matrix<T> result =  new Matrix<T>(this.getRowCount(), matrix.getColCount(), calculator);

        // Validation
        if (this.getColCount() != matrix.getRowCount())
            throw new IllegalArgumentException("Matrices are not consistent");

        for (int i = 0; i < this.getRowCount(); i++){
            for (int j = 0; j < matrix.getColCount(); j++){
                result.set(i, j, this.getRow(i).multiply(matrix.getCol(j)).sumElements());
            }
        }
        return result;
    }
}
