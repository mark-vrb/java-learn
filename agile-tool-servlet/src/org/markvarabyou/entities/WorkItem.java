package org.markvarabyou.entities;

import org.markvarabyou.entities.enums.WorkItemType;

import java.util.Date;

/**
 * WorkItem entity.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 1:16 PM
 */
public class WorkItem {
    private int id;
    private String name;
    private String description;
    private Date creationDate;
    private int createdByUserId;
    private int assigneeUserId;
    private byte size;
    private WorkItemType type;
    private int boardId;
    private int boardColumnId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public int getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(int assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
    }

    public byte getSize() {
        return size;
    }

    public void setSize(byte size) {
        this.size = size;
    }

    public WorkItemType getType() {
        return type;
    }

    public void setType(WorkItemType type) {
        this.type = type;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getBoardColumnId() {
        return boardColumnId;
    }

    public void setBoardColumnId(int boardColumnId) {
        this.boardColumnId = boardColumnId;
    }

    public WorkItem(){}

    public WorkItem(String name, String description, Date creationDate,
                    int createdByUserId, int assigneeUserId, byte size,
                    WorkItemType type, int boardId, int boardColumnId){
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.createdByUserId = createdByUserId;
        this.assigneeUserId = assigneeUserId;
        this.size = size;
        this.type = type;
        this.boardId = boardId;
        this.boardColumnId = boardColumnId;
    }
}
