package org.markvarabyou.services.exceptions;

/**
 * Exception from Dao layer.
 * User: Mark Varabyou
 * Date: 11/23/13
 * Time: 12:19 PM
 */
public class InternalDaoException extends ServiceException {
    public InternalDaoException() {}

    public InternalDaoException(Exception inner) {
        super(inner);
    }
}
