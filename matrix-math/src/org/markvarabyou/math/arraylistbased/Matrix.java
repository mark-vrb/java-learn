package org.markvarabyou.math.arraylistbased;

import java.util.ArrayList;

/**
 * Abstract generic class for math matrix.
 * Author: Mark Vorobyov
 * Date: 9/9/13
 * Time: 3:44 PM
 */
public abstract class Matrix<T> {

    private ArrayList<T> elements;
    private int rowCount;
    private int colCount;

    /**
     * Initializes new matrix rowCount x colCount with values from array
     * @param rowCount Number of rows in new matrix
     * @param colCount Number of columns in new matrix
     * @param array Array with values to initialize matrix
     */
    public Matrix(int rowCount, int colCount, T[] array) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        if (!isInRange(rowCount*colCount, 0, array.length))
            throw new IllegalArgumentException("Invalid array length");
        elements = new ArrayList<T>();
        for (T element : array){
            elements.add(element);
        }
    }

    public int getRowCount(){
        return rowCount;
    }

    public int getColCount(){
        return colCount;
    }

    public T get(int row, int column){
        if (!isInRange(row, 0, rowCount) || !isInRange(column, 0, colCount))
            throw new IllegalArgumentException("Invalid row or column index");
        return elements.get(row * colCount + column);
    }

    public T set(int row, int column, T value){
        if (!isInRange(row, 0, rowCount) || !isInRange(column, 0, colCount))
            throw new IllegalArgumentException("Invalid row or column index");
        return elements.set(row * colCount + column, value);
    }

    public Matrix<T> add(Matrix<T> matrix){
        if (this.rowCount != matrix.getRowCount() || this.colCount != matrix.getColCount()){
            throw new IllegalArgumentException("Illegal size of matrix to add");
        }
        for (int i = 0; i < getRowCount(); i++){
            for (int j = 0; j < getColCount(); j++)
                this.set(i, j, sumElements(this.get(i, j), matrix.get(i, j)));
        }
        return this;
    }

    public Matrix<T> multiply(T value){
        for (int i = 0; i < getRowCount(); i++){
            for (int j = 0; j < getColCount(); j++)
                this.set(i, j, multiplyElements(this.get(i, j), value));
        }
        return this;
    }

    public Matrix<T> multiply(Matrix<T> matrix){
        return this; //TODO: add implementation
    }

    private boolean isInRange(int value, int left, int right){
        return value >= left && value <= right;
    }

    protected abstract T sumElements(T first, T second);
    protected abstract T multiplyElements(T first, T second);
}
