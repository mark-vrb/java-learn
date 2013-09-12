package org.markvarabyou.math.arraylistbased;

/**
 * Created with IntelliJ IDEA.
 * User: mark
 * Date: 9/13/13
 * Time: 1:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class IntMatrix extends Matrix<Integer> {
    /**
     * Initializes new matrix rowCount x colCount with values from array
     *
     * @param rowCount Number of rows in new matrix
     * @param colCount Number of columns in new matrix
     * @param array    Array with values to initialize matrix
     */
    public IntMatrix(int rowCount, int colCount, Integer[] array) {
        super(rowCount, colCount, array);
    }

    @Override
    protected Integer sumElements(Integer first, Integer second) {
        return first + second;
    }

    @Override
    protected Integer multiplyElements(Integer first, Integer second) {
        return first * second;
    }
}
