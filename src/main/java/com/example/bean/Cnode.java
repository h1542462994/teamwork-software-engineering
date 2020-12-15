package main.java.com.example.bean;

import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cnode")
@IdClass(CnodeKey.class)
public class Cnode {
    @Id
    @Column(name = "coursesid")
    private Integer coursesid;

    @Id
    @Column(name = "nodeid")
    private Integer nodeid;
    private String nname;
    private String path;
    private String type;

    public Integer getCoursesid() {
        return coursesid;
    }

    public void setCoursesid(Integer coursesid) {
        this.coursesid = coursesid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
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
