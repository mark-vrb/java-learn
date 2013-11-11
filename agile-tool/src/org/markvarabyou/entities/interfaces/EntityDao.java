package org.markvarabyou.entities.interfaces;

import java.util.ArrayList;

/**
 * Data Access Object Interface for entities of agile-tool.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:34 PM
 */
public interface EntityDao<T> {
    T create(T entity);
    T read(int id);
    ArrayList<T> read();
    T update(T entity);
    boolean delete(int id);
}
