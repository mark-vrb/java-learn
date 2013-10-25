package org.markvarabyou.Entities;

import org.markvarabyou.Entities.Enums.WorkItemType;

import java.util.Date;
import java.util.UUID;

/**
 * WorkItem entity.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 1:16 PM
 */
public class WorkItem {
    private UUID id;
    private String name;
    private String description;
    private Date creationDate;
    private UUID createdByUserId;
    private UUID assigneeUserId;
    private int size;
    private WorkItemType type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public UUID getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(UUID createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public UUID getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(UUID assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public WorkItemType getType() {
        return type;
    }

    public void setType(WorkItemType type) {
        this.type = type;
    }
}
