package org.markvarabyou.entities.interfaces;

import org.markvarabyou.entities.exceptions.DaoException;

import java.util.ArrayList;

/**
 * Data Access Object Interface for entities of agile-tool.
 * User: Mark Varabyou
 * Date: 11/2/13
 * Time: 7:34 PM
 */
public interface EntityDao<T> {
    T create(T entity) throws DaoException;
    T read(int id) throws DaoException;
    ArrayList<T> read() throws DaoException;
    T update(T entity) throws DaoException;
    boolean delete(int id) throws DaoException;
}
