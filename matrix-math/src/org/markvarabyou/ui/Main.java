package org.markvarabyou.ui;

import org.markvarabyou.math.arraylistbased.IntMatrix;

public class Main {

    public static void main(String[] args) {
        IntMatrix matrix = new IntMatrix(2, 2, new Integer[]{1, 2, 3, 4});
        System.out.println(matrix.getColCount());
    }
}
