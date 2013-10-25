package org.markvarabyou.Entities;

import java.util.Date;
import java.util.UUID;

/**
 * Board entity, set of BoardColumns.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 4:03 PM
 */
public class Board {
    private UUID id;
    private String name;
    private UUID createdByUserId;
    private Date creationDate;

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

    public UUID getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(UUID createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
