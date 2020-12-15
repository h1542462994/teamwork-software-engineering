package main.java.com.example.bean;


import javax.persistence.Embeddable;
import java.io.Serializable;

public class CnodeKey implements Serializable {
    private Integer coursesid;
    private Integer nodeid;

    public Integer getCoursesid() {
        return coursesid;
    }

    public void setCoursesid(Integer coursesid) {
        this.coursesid = coursesid;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }
}
