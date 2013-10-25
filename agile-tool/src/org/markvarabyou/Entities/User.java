package org.markvarabyou.Entities;

import java.util.UUID;

/**
 * User entity for agile tool system.
 * User: Mark Varabyou
 * Date: 10/25/13
 * Time: 4:07 PM
 */
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
