package org.markvarabyou.math.common;

/**
 * Calculator for performing matrix and vector elements math operations.
 * Author: Mark Vorobyov
 * Date: 9/13/13
 * Time: 5:12 PM
 */
public abstract class Calculator<T> {

    /**
     * Gets initial value for element
     * @return initial value
     */
    public abstract T getNew();

    /**
     * Sums two elements
     * @param first first element
     * @param second second element
     * @return sum of elements
     */
    public abstract T sum(T first, T second);

    /**
     * Multiplies two elements
     * @param first first element
     * @param second second element
     * @return result of multiplication
     */
    public abstract T mul(T first, T second);

    /**
     * Decodes element from string
     * @param s string to decode
     * @return decoded element
     */
    public abstract T decode(String s);
}
