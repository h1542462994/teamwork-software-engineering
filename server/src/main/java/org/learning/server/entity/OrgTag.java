package org.learning.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * a tag for org, tag is to display the index of the org
 */
@Entity
public class OrgTag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orgNodeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgNodeId() {
        return orgNodeId;
    }

    public void setOrgNodeId(Integer orgNodeId) {
        this.orgNodeId = orgNodeId;
    }
}
