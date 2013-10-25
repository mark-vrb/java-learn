package org.markvarabyou.Entities;

import org.markvarabyou.Entities.Enums.BoardColumnType;

import java.util.UUID;

/**
 * BoardColumn entity, holding set of WorkItems.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 4:00 PM
 */
public class BoardColumn {
    private int id;
    private String name;
    private int boardId;
    private BoardColumnType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public BoardColumnType getType() {
        return type;
    }

    public void setType(BoardColumnType type) {
        this.type = type;
    }
}
