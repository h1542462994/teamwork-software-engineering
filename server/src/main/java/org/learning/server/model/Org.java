package org.learning.server.model;

import java.util.LinkedList;
import java.util.List;

public class Org extends OrgType {
    public Org() {}
    public Org(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public Org(Integer id, String name, String description) {
        this(name, description);
        this.setId(id);
    }
    public Org addDepartment(Department department) {
        this.departments.add(department);
        return this;
    }

    private List<Department> departments = new LinkedList<>();

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
