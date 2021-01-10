package org.learning.server.form;

import javax.validation.constraints.NotNull;

public class CourseOpenForm extends CourseSelectForm {
    @NotNull
    private String endTime;
    @NotNull
    private boolean isEdit;
    @NotNull
    private String startTime;

    @NotNull
    private String orgNode;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOrgNode() {
        return orgNode;
    }

    public void setOrgNode(String orgNode) {
        this.orgNode = orgNode;
    }
}
