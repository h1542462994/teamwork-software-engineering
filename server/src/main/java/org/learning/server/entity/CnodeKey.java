package org.learning.server.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

public class CnodeKey implements Serializable {
    private Integer courseid;
    private Integer nodeid;

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }
}
