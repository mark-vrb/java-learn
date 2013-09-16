package org.markvarabyou.math.common;

/**
 * Exception helper class for Matrix class.
 * Author: Mark Vorobyov
 * Date: 9/16/13
 * Time: 5:13 PM
 */
public class MatrixExceptionHelper {
    private boolean isInRange(int value, int left, int right){
        return value >= left && value <= right;
    }

    public void checkLength(int actualLength, int desiredLength, String entity){
        if (actualLength != desiredLength)
            throw new IllegalArgumentException(String.format("Invalid %s length", entity));
    }

    public void checkRange(int index, int count) {
        if (!isInRange(index, 0, count))
            throw new IllegalArgumentException("Index is out of range");
    }

    public void checkRowAndColCount(int row, int col, int rowCount, int colCount) {
        if (!isInRange(row, 0, rowCount) || !isInRange(col, 0, colCount))
            throw new IllegalArgumentException("Invalid row or column index");
    }
}
