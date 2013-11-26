package org.markvarabyou.services.exceptions;

/**
 * Exception for not founded user.
 * User: Mark Varabyou
 * Date: 11/21/13
 * Time: 2:08 PM
 */
public class EntityNotFoundException extends ServiceException {
    public EntityNotFoundException() {}

    public EntityNotFoundException(Exception inner) {
        super(inner);
    }
}
