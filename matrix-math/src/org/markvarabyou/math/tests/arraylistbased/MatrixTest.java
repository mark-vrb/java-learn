package org.markvarabyou.math.tests.arraylistbased;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import org.markvarabyou.math.arraylistbased.Matrix;
import org.markvarabyou.math.arraylistbased.Vector;
import org.markvarabyou.math.common.Calculators.DoubleCalculator;

/**
 * Test class for Matrix
 * Author: Mark Vorobyov
 * Date: 9/16/13
 * Time: 6:55 PM
 */
public class MatrixTest {
    private Matrix<Double> doubleMatrix;

    //Test data
    private Double[] initialValues = new Double[]{1.0, 2.0, 3.0, 4.0};
    private int initRowCount = 2;
    private int initColCount = 2;
    private DoubleCalculator calculator = new DoubleCalculator();

    private Matrix<Double> restoreTestMatrix(){
        return new Matrix<Double>(initRowCount, initColCount, initialValues, calculator);
    }

    @Before
    public void setUp() throws Exception {
        doubleMatrix = restoreTestMatrix();
    }

    @Test
    public void testGetRowCount() throws Exception {
        //When
        int rowCount  = doubleMatrix.getRowCount();

        //Then
        Assert.assertEquals(initRowCount, rowCount);
    }

    @Test
    public void testGetColCount() throws Exception {
        //When
        int colCount  = doubleMatrix.getColCount();

        //Then
        Assert.assertEquals(initColCount, colCount);
    }

    @Test
    public void testGet() throws Exception {
        //When
        double element  = doubleMatrix.get(0, 0);

        //Then
        Assert.assertEquals(initialValues[0], element);
    }

    @Test
    public void testSet() throws Exception {
        //Given
        double initValue = doubleMatrix.get(0, 0);

        //When
        doubleMatrix.set(0, 0, initValue + 1);

        //Then
        double setValue = doubleMatrix.get(0, 0);
        Assert.assertEquals(initValue + 1, setValue);
    }

    @Test
    public void testGetRow() throws Exception {
        //When
        Vector<Double> row = doubleMatrix.getRow(0);

        //Then
        Assert.assertEquals(doubleMatrix.getColCount(), row.getLength());
        for (int i = 0; i < doubleMatrix.getColCount(); i++){
            Assert.assertEquals(doubleMatrix.get(0, i), row.get(i));
        }
    }

    @Test
    public void testSetRow() throws Exception {
        //Given
        Vector<Double> newRow = doubleMatrix.getRow(0).multiply(doubleMatrix.getRow(0));

        //When
        doubleMatrix.setRow(0, newRow);

        //Then
        for (int i = 0; i < doubleMatrix.getColCount(); i++){
            Assert.assertEquals(doubleMatrix.get(0, i), newRow.get(i));
        }
    }

    @Test
    public void testGetCol() throws Exception {
        //When
        Vector<Double> col = doubleMatrix.getCol(0);

        //Then
        Assert.assertEquals(doubleMatrix.getRowCount(), col.getLength());
        for (int i = 0; i < doubleMatrix.getRowCount(); i++){
            Assert.assertEquals(doubleMatrix.get(i, 0), col.get(i));
        }
    }

    @Test
    public void testSetCol() throws Exception {
        //Given
        Vector<Double> newCol = doubleMatrix.getCol(0).multiply(doubleMatrix.getCol(0));

        //When
        doubleMatrix.setCol(0, newCol);

        //Then
        for (int i = 0; i < doubleMatrix.getRowCount(); i++){
            Assert.assertEquals(doubleMatrix.get(i, 0), newCol.get(i));
        }
    }

    @Test
    public void testAdd() throws Exception {
        //When
        Matrix<Double> result = doubleMatrix.add(doubleMatrix);

        //Then
        for (int i = 0; i < result.getRowCount(); i++){
            for (int j = 0; j < result.getColCount(); j++){
                Assert.assertEquals(result.get(i, j), doubleMatrix.get(i, j) * 2);
            }
        }
    }

    @Test
    public void testMultiplyByNumber() throws Exception {
        //Given
        double number = 2;

        //When
        Matrix<Double> result = doubleMatrix.multiply(number);

        //Then
        for (int i = 0; i < result.getRowCount(); i++){
            for (int j = 0; j < result.getColCount(); j++){
                Assert.assertEquals(result.get(i, j), doubleMatrix.get(i, j) * 2);
            }
        }
    }

    @Test
    public void testMultiplyByMatrix() throws Exception {
        //Given
        Matrix<Double> first = new Matrix<Double>(2, 2, new Double[]{1.0, 6.0, 3.0, 8.0}, calculator);
        Matrix<Double> second = new Matrix<Double>(2, 2, new Double[]{2.0, 2.0, 9.0, 7.0}, calculator);

        Matrix<Double> expectedResult = new Matrix<Double>(2, 2, new Double[]{56.0, 44.0, 78.0, 62.0}, calculator);

        //When
        Matrix<Double> actualResult = first.multiply(second);

        //Then
        for (int i = 0; i < actualResult.getRowCount(); i++){
            for (int j = 0; j < actualResult.getColCount(); j++){
                Assert.assertEquals(expectedResult.get(i, j), actualResult.get(i, j));
            }
        }
    }
}
