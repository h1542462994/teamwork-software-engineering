package com.po;

import java.util.List;

public class Person {
    private Integer pid;
    private String pname;
    private String dep;
    private String ltime;
    private List<Courses> courses;

    public Person() {
    }

    public Person(Integer pid, String pname, String dep, String ltime, List<Courses> courses) {
        this.pid = pid;
        this.pname = pname;
        this.dep = dep;
        this.ltime = ltime;
        this.courses = courses;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getLtime() {
        return ltime;
    }

    public void setLtime(String ltime) {
        this.ltime = ltime;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", dep='" + dep + '\'' +
                ", ltime='" + ltime + '\'' +
                ", courses=" + courses +
                '}';
    }
}
