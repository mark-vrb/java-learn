package org.markvarabyou.Entities;

import org.markvarabyou.Entities.Enums.BoardUserRoleType;

import java.util.UUID;

/**
 * Board - User relation many-to-many entity.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 4:10 PM
 */
public class BoardUserRole {
    private UUID boardId;
    private UUID userId;
    private BoardUserRoleType type;
}
