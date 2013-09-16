package org.markvarabyou.math.tests.linkedlistbased;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.markvarabyou.math.linkedlistbased.Matrix;
import org.markvarabyou.math.linkedlistbased.Vector;
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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
    public void testGetSuccess() throws Exception {
        //When
        double element  = doubleMatrix.get(0, 0);

        //Then
        Assert.assertEquals(initialValues[0], element);
    }

    @Test
    public void testGetFails() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid row or column index");

        //When
        doubleMatrix.get(doubleMatrix.getRowCount() + 1, 0); //Wrong row index
    }

    @Test
    public void testSetSuccess() throws Exception {
        //Given
        double initValue = doubleMatrix.get(0, 0);

        //When
        doubleMatrix.set(0, 0, initValue + 1);

        //Then
        double setValue = doubleMatrix.get(0, 0);
        Assert.assertEquals(initValue + 1, setValue);
    }

    @Test
    public void testSetFails() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid row or column index");
        double initValue = doubleMatrix.get(0, 0);

        //When
        doubleMatrix.set(0, doubleMatrix.getColCount() + 1, initValue + 1); //Wrong column index
    }

    @Test
    public void testGetRowSuccess() throws Exception {
        //When
        Vector<Double> row = doubleMatrix.getRow(0);

        //Then
        Assert.assertEquals(doubleMatrix.getColCount(), row.getLength());
        for (int i = 0; i < doubleMatrix.getColCount(); i++){
            Assert.assertEquals(doubleMatrix.get(0, i), row.get(i));
        }
    }

    @Test
    public void testGetRowFails() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Index is out of range");

        //When
        doubleMatrix.getRow(-1); //Wrong row index
    }

    @Test
    public void testSetRowSuccess() throws Exception {
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
    public void testSetRowFailsOutOfRange() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Index is out of range");
        Vector<Double> newRow = doubleMatrix.getRow(0).multiply(doubleMatrix.getRow(0));

        //When
        doubleMatrix.setRow(-1, newRow); //Wrong row index
    }

    @Test
    public void testSetRowFailsInvalidVectorLength() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid vector length");
        Vector<Double> newRow = new Vector<Double>(doubleMatrix.getColCount() - 1, calculator); //Vector of invalid size

        //When
        doubleMatrix.setRow(0, newRow);
    }

    @Test
    public void testGetColSuccess() throws Exception {
        //When
        Vector<Double> col = doubleMatrix.getCol(0);

        //Then
        Assert.assertEquals(doubleMatrix.getRowCount(), col.getLength());
        for (int i = 0; i < doubleMatrix.getRowCount(); i++){
            Assert.assertEquals(doubleMatrix.get(i, 0), col.get(i));
        }
    }

    @Test
    public void testGetColFails() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Index is out of range");

        //When
        doubleMatrix.getCol(-1); //Wrong column index
    }

    @Test
    public void testSetColSuccess() throws Exception {
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
    public void testSetColFailsOutOfRange() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Index is out of range");
        Vector<Double> newCol = doubleMatrix.getCol(0).multiply(doubleMatrix.getCol(0));

        //When
        doubleMatrix.setCol(-1, newCol); //Wrong column index
    }

    @Test
    public void testSetColFailsInvalidVectorLength() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid vector length");
        Vector<Double> newCol = new Vector<Double>(doubleMatrix.getRowCount() - 1, calculator); //Vector of invalid size

        //When
        doubleMatrix.setCol(0, newCol);
    }

    @Test
    public void testAddSuccess() throws Exception {
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
    public void testAddFailsInvalidMatrix() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal size of matrix to add");
        Matrix<Double> matrix =
                new Matrix<Double>(doubleMatrix.getRowCount() - 1, doubleMatrix.getColCount() - 1, calculator);

        //When
        doubleMatrix.add(matrix); //Add matrix of another size
    }

    @Test
    public void testMultiplyMatrixByNumber() throws Exception {
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
    public void testMultiplyMatrices() throws Exception {
        //Given
        Double[] values1 = new Double[] {
                2.0, 3.0, 4.0, 5.0,
                3.0, 4.0, 5.0, 5.0,
                2.0, 7.0, 8.0, 9.0,
                3.0, 1.0, 7.0, 9.0
        };
        Double[] values2 = new Double[] {
                6.0, 3.0, 5.0, 7.0,
                8.0, 1.0, 2.0, 9.0,
                4.0, 9.0, 1.0, 2.0,
                3.0, 4.0, 5.0, 2.0
        };
        Double[] expectedValues = new Double[] {
                67.0,  65.0,  45.0,  59.0,
                85.0,  78.0,  53.0,  77.0,
                127.0, 121.0,  77.0, 111.0,
                81.0, 109.0,  69.0,  62.0
        };
        Matrix<Double> first = new Matrix<Double>(4, 4, values1, calculator);
        Matrix<Double> second = new Matrix<Double>(4, 4, values2, calculator);
        Matrix<Double> expected = new Matrix<Double>(4, 4, expectedValues, calculator);

        //When
        Matrix<Double> actual = first.multiply(second);

        //Then
        for (int i = 0; i < actual.getRowCount(); i++){
            for (int j = 0; j < actual.getColCount(); j++){
                Assert.assertEquals(expected.get(i, j), actual.get(i, j));
            }
        }
    }

    @Test
    public void testMultiplyZeroMatrices() throws Exception {
        //Given
        Double[] values = new Double[] {
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0
        };
        Matrix<Double> first = new Matrix<Double>(4, 4, values, calculator);
        Matrix<Double> second = new Matrix<Double>(4, 4, values, calculator);
        Matrix<Double> expected = new Matrix<Double>(4, 4, values, calculator);

        //When
        Matrix<Double> actual = first.multiply(second);

        //Then
        for (int i = 0; i < actual.getRowCount(); i++){
            for (int j = 0; j < actual.getColCount(); j++){
                Assert.assertEquals(expected.get(i, j), actual.get(i, j));
            }
        }
    }

    @Test
    public void testMultiplyIdentityMatrices() throws Exception {
        //Given
        Double[] values = new Double[] {
                1.0, 0.0, 0.0, 0.0,
                0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0,
                0.0, 0.0, 0.0, 1.0
        };
        Matrix<Double> first = new Matrix<Double>(4, 4, values, calculator);
        Matrix<Double> second = new Matrix<Double>(4, 4, values, calculator);
        Matrix<Double> expected = new Matrix<Double>(4, 4, values, calculator);

        //When
        Matrix<Double> actual = first.multiply(second);

        //Then
        for (int i = 0; i < actual.getRowCount(); i++){
            for (int j = 0; j < actual.getColCount(); j++){
                Assert.assertEquals(expected.get(i, j), actual.get(i, j));
            }
        }
    }

    @Test
    public void testMultiplyMatricesFails() throws Exception {
        //Given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Matrices are not consistent");
        Matrix<Double> first = new Matrix<Double>(4, 5, calculator);
        Matrix<Double> second = new Matrix<Double>(6, 4, calculator);

        //When
        first.multiply(second); //Trying to multiply non consistent matrices
    }
}
