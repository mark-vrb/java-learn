package org.markvarabyou.math.tests.arraylistbased;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.markvarabyou.math.arraylistbased.Vector;
import org.markvarabyou.math.common.Calculators.DoubleCalculator;

/**
 * Test class for Vector.
 * Author: Mark Vorobyov
 * Date: 9/16/13
 * Time: 9:48 PM
 */
public class VectorTest {
    private Vector<Double> doubleVector;

    //Init data
    private Double[] initialValues = new Double[] {1.0,2.0, 3.0, 4.0};
    private DoubleCalculator calculator = new DoubleCalculator();

    @Before
    public void setUp() throws Exception {
        doubleVector = new Vector<Double>(initialValues, calculator);
    }

    @Test
    public void testGet() throws Exception {
        //Given
        int index = 0;

        //When
        double value = doubleVector.get(index);

        //Then
        Assert.assertEquals(initialValues[index], value);
    }

    @Test
    public void testSet() throws Exception {
        //Given
        int index = 0;
        double value = 12;

        //When
        doubleVector.set(index, value);

        //Then
        Assert.assertEquals(value, doubleVector.get(index));
    }

    @Test
    public void testGetLength() throws Exception {
        //Given
        int expected = initialValues.length;

        //When
        int actual = doubleVector.getLength();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSumElements() throws Exception {
        //Given
        double expected = 0;
        for (Double initialValue : initialValues) {
            expected += initialValue;
        }

        //When
        double actual = doubleVector.sumElements();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMultiply() throws Exception {
        //Given
        Double[] firstValues = new Double[] {2.0, 4.0, 5.0, 3.0};
        Double[] secondValues = new Double[] {5.0, 2.0, 1.0, 7.0};
        Double[] expectedValues = new Double[] {10.0, 8.0, 5.0, 21.0};
        Vector<Double> first = new Vector<Double>(firstValues, calculator);
        Vector<Double> second = new Vector<Double>(secondValues, calculator);
        Vector<Double> expected = new Vector<Double>(expectedValues, calculator);

        //When
        Vector<Double> actual = first.multiply(second);

        //Then
        for (int i = 0; i < expected.getLength(); i++){
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }
}
