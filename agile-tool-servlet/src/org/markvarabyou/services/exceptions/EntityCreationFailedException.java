package org.markvarabyou.services.exceptions;

/**
 * Exception for failed creation case because of incorrect data.
 * User: Mark Varabyou
 * Date: 11/21/13
 * Time: 2:13 PM
 */
public class EntityCreationFailedException extends ServiceException {
    public EntityCreationFailedException() {}

    public EntityCreationFailedException(Exception inner) {
        super(inner);
    }
}
