package org.markvarabyou.entities.interfaces;

import org.markvarabyou.entities.exceptions.DaoException;

import java.util.ArrayList;

/**
 * Interface for many-to-many relational entity of agile-tool dao.
 * User: Mark Varabyou
 * Date: 11/6/13
 * Time: 8:35 PM
 */
public interface RelationEntityDao<T> {
    T create(T entity) throws DaoException;
    T read(int firstId, int secondId) throws DaoException;
    ArrayList<T> read() throws DaoException;
    T update(T entity) throws DaoException;
    boolean delete(int firstId, int secondId) throws DaoException;
}
