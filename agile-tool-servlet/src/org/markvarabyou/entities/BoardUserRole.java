package org.markvarabyou.entities;

import org.markvarabyou.entities.enums.BoardUserRoleType;

/**
 * Board - User relation many-to-many entity.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 4:10 PM
 */
public class BoardUserRole {
    private int boardId;
    private int userId;
    private BoardUserRoleType type;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BoardUserRoleType getType() {
        return type;
    }

    public void setType(BoardUserRoleType type) {
        this.type = type;
    }

    public BoardUserRole(){}

    public BoardUserRole(int boardId, int userId, BoardUserRoleType type){
        this.boardId = boardId;
        this.userId = userId;
        this.type = type;
    }
}
