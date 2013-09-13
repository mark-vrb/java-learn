package org.markvarabyou.math.arraylistbased;

import org.markvarabyou.math.common.Calculator;

import java.util.ArrayList;

/**
 * Class for mathematical vector.
 * Author: Mark Vorobyov
 * Date: 9/9/13
 * Time: 4:03 PM
 */
public class Vector<T> {

    private ArrayList<T> elements;
    private int length;
    private Calculator<T> calculator;

    public Vector(T[] elements, Calculator<T> calculator) {
        this.length = elements.length;
        this.calculator = calculator;
        this.elements = new ArrayList<T>();
        for (T element : elements){
            this.elements.add(element);
        }
    }

    public Vector(ArrayList<T> elements, Calculator<T> calculator) {
        this.length = elements.size();
        this.calculator = calculator;
        this.elements = new ArrayList<T>();
        for (T element : elements){
            this.elements.add(element);
        }
    }

    public Vector(int length, Calculator<T> calculator) {
        this.length = length;
        this.calculator = calculator;
        this.elements = new ArrayList<T>();
        for (int i = 0; i < length; i++){
            this.elements.add(calculator.getNew());
        }
    }

    public T get(int index){
        return elements.get(index);
    }

    public void set(int index, T value){
        elements.set(index, value);
    }

    public T sumElements(){
        T sum = calculator.getNew();
        for (T element : elements){
            sum = calculator.sum(sum, element);
        }
        return sum;
    }

    public Vector<T> multiply(Vector<T> vector){
        Vector<T> result = new Vector<T>(length, calculator);
        for (int i = 0; i < length; i++){
            result.set(i, calculator.sum(get(i), vector.get(i)));
        }
        return result;
    }
}
