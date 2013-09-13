package org.markvarabyou.math.common;

/**
 * Calculator for performing matrix and vector elements operations.
 * Author: Mark Vorobyov
 * Date: 9/13/13
 * Time: 5:12 PM
 */
public abstract class Calculator<T> {
    public abstract T getNew();
    public abstract T sum(T first, T second);
    public abstract T mul(T first, T second);
}
