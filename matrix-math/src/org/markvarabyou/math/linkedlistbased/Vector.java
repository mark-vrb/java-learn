package org.markvarabyou.math.linkedlistbased;

import org.markvarabyou.math.common.Calculator;

import java.util.AbstractList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Class for mathematical vector based on LinkedList.
 * Author: Mark Vorobyov
 * Date: 9/9/13
 * Time: 4:03 PM
 */
public class Vector<T> {

    private LinkedList<T> elements;
    private int length;
    private Calculator<T> calculator;

    /**
     * Initializes new vector object with values from array and calculator
     * @param elements array of vector values
     * @param calculator object to perform math operations with values
     */
    public Vector(T[] elements, Calculator<T> calculator) {
        this.length = elements.length;
        this.calculator = calculator;
        this.elements = new LinkedList<T>();
        Collections.addAll(this.elements, elements);
    }

    /**
     * Initializes new vector object with values from list and calculator
     * @param elements list with vector values
     * @param calculator object to perform math operations with values
     */
    public Vector(AbstractList<T> elements, Calculator<T> calculator) {
        this.length = elements.size();
        this.calculator = calculator;
        this.elements = new LinkedList<T>();
        for (T element : elements){
            this.elements.add(element);
        }
    }

    /**
     * Initializes new vector object with specified length and calculator. Fills them with initial values
     * @param length count of elements in vector
     * @param calculator object to perform math operations with values
     */
    public Vector(int length, Calculator<T> calculator) {
        this.length = length;
        this.calculator = calculator;
        this.elements = new LinkedList<T>();
        for (int i = 0; i < length; i++){
            this.elements.add(calculator.getNew());
        }
    }

    /**
     * Gets value of element with specified index
     * @param index index of element
     * @return value of selected element
     */
    public T get(int index){
        return elements.get(index);
    }

    /**
     * Sets value to element with specified index
     * @param index index of element
     * @param value value to set
     */
    public void set(int index, T value){
        elements.set(index, value);
    }

    /**
     * Gets length of current vector
     * @return length of current vector
     */
    public int getLength(){
        return length;
    }

    /**
     * Sums elements values from vector
     * @return sum of elements
     */
    public T sumElements(){
        T sum = calculator.getNew();
        for (T element : elements){
            sum = calculator.sum(sum, element);
        }
        return sum;
    }

    /**
     * Multiplies current vector to specified vector
     * @param vector vector to multiply by
     * @return result vector
     */
    public Vector<T> multiply(Vector<T> vector){
        Vector<T> result = new Vector<T>(length, calculator);
        for (int i = 0; i < length; i++){
            result.set(i, calculator.mul(get(i), vector.get(i)));
        }
        return result;
    }
}
