package org.markvarabyou.services.entities;

import java.util.ArrayList;

/**
 * Helper entity for collection result of query.
 * User: Mark Varabyou
 * Date: 11/21/13
 * Time: 2:22 PM
 */
public class CollectionResult<T> {
    private ArrayList<T> items;

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    public CollectionResult(ArrayList<T> items) {
        this.items = items;
    }
}
