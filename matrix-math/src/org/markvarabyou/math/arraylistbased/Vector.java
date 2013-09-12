package org.markvarabyou.math.arraylistbased;

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

    public Vector(T[] elements) {
        this.elements = new ArrayList<T>();
        this.length = elements.length;
        for (T element : elements){
            this.elements.add(element);
        }
    }
}
