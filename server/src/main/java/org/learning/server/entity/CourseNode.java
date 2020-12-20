package org.learning.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="coursenode")
@IdClass(CnodeKey.class)
public class CourseNode {
    @Id
    @Column(name = "courseid")
    private Integer courseid;


    @Id
    @Column(name = "nodeid")
    private Integer nodeid;
    private String name;
    private String path;
    private String type;

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
