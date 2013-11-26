package org.markvarabyou.services.exceptions;

/**
 * Base class for all operations from services.
 * User: Mark Varabyou
 * Date: 11/23/13
 * Time: 12:43 PM
 */
public abstract class ServiceException extends Exception {
    private Exception inner = null;

    public Exception getInner() {
        return inner;
    }

    public ServiceException() {}

    public ServiceException(Exception inner) {
        this.inner = inner;
    }
}
