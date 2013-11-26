package org.markvarabyou.services.exceptions;

/**
 * Exception for update operation with entity.
 * User: Mark Varabyou
 * Date: 11/21/13
 * Time: 2:17 PM
 */
public class EntityUpdateFailedException extends ServiceException {
    public EntityUpdateFailedException() {}

    public EntityUpdateFailedException(Exception inner) {
        super(inner);
    }
}
