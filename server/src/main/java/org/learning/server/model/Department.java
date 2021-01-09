package org.learning.server.model;

@Deprecated
public class Department extends Group {
    public Department() {

    }

    public Department(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public Department(Integer id, String name, String description) {
        this(name, description);
        this.setId(id);
    }
}
