package org.markvarabyou.math.common.Calculators;

import org.markvarabyou.math.common.Calculator;

/**
 * Calculator realization for double elements.
 * Author: Mark Vorobyov
 * Date: 9/16/13
 * Time: 7:09 PM
 */
public class DoubleCalculator extends Calculator<Double> {
    @Override
    public Double getNew() {
        return (double) 0;
    }

    @Override
    public Double sum(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double mul(Double first, Double second) {
        return first * second;
    }

    @Override
    public Double decode(String s) {
        return Double.parseDouble(s);
    }
}
