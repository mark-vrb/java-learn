package org.markvarabyou.math.common.Calculators;

import org.markvarabyou.math.common.Calculator;

/**
 * Calculator realization for int elements.
 * Author: Mark Vorobyov
 * Date: 9/16/13
 * Time: 7:02 PM
 */
public class IntCalculator extends Calculator<Integer> {
    @Override
    public Integer getNew() {
        return 0;
    }

    @Override
    public Integer sum(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer mul(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Integer decode(String s) {
        return Integer.parseInt(s);
    }
}
