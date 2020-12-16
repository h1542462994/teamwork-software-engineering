package org.learning.server.model;

import java.util.LinkedList;
import java.util.List;

public class Department extends OrgType {
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
