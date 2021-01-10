package org.learning.server.form;

import javax.validation.constraints.NotNull;

public class CourseSelectForm {
    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
