package org.markvarabyou.entities.interfaces;

import java.util.ArrayList;

/**
 * Interface for many-to-many relational entity of agile-tool dao.
 * User: Mark Varabyou
 * Date: 11/6/13
 * Time: 8:35 PM
 */
public interface RelationEntityDao<T> {
    T create(T entity);
    T read(int firstId, int secondId);
    ArrayList<T> read();
    T update(T entity);
    boolean delete(int firstId, int secondId);
}
