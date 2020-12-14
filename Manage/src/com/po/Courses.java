package com.po;

import java.util.Date;
import java.util.List;

public class Courses {

    private Integer cid;
    private String cname;
    private String info;
    private String time;
    private Date stime;
    private Date etime;
    private List<Person> persons;

    public Courses() {
    }

    public Courses(Integer cid, String cname, String info, String time, Date stime, Date etime, List<Person> persons) {
        this.cid = cid;
        this.cname = cname;
        this.info = info;
        this.time = time;
        this.stime = stime;
        this.etime = etime;
        this.persons = persons;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", info='" + info + '\'' +
                ", time='" + time + '\'' +
                ", stime=" + stime +
                ", etime=" + etime +
                ", persons=" + persons +
                '}';
    }
}
