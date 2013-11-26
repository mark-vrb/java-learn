package org.markvarabyou.entities.exceptions;

/**
 * Exception for Dao layer.
 * User: Mark Varabyou
 * Date: 11/23/13
 * Time: 11:57 AM
 */
public class DaoException extends Exception {
    private Exception inner;

    public DaoException(Exception inner) {
        this.inner = inner;
    }

    public Exception getInner() {
        return inner;
    }
}
