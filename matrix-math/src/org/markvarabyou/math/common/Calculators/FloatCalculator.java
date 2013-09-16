package org.markvarabyou.math.common.Calculators;

import org.markvarabyou.math.common.Calculator;

/**
 * Calculator realization for float elements.
 * Author: Mark Vorobyov
 * Date: 9/16/13
 * Time: 7:02 PM
 */
public class FloatCalculator extends Calculator<Float> {
    @Override
    public Float getNew() {
        return new Float(0);
    }

    @Override
    public Float sum(Float first, Float second) {
        return first + second;
    }

    @Override
    public Float mul(Float first, Float second) {
        return first * second;
    }
}
