package org.software.learning.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String uid;
    private Long type;
    private String groupType;
    private String name;
    @JsonIgnore
    private String password;
    private String email;

    public static final Long NORMAL = 0L;
    public static final Long ADMIN = 1L;
    public static final String GROUP_NORMAL = "normal";

    public User(){

    }
    public User(String uid, String password){
        this.uid = uid;
        this.password = password;
    }

    public User(String uid, Long type, String groupType, String name, String password, String email) {
        this.uid = uid;
        this.type = type;
        this.groupType = groupType;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
