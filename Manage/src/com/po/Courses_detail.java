package com.po;

public class Courses_detail {
    private Integer id;
    private Integer courses_id;
    private Integer person_id;

    public Courses_detail() {
    }

    public Courses_detail(Integer id, Integer courses_id, Integer person_id) {
        this.id = id;
        this.courses_id = courses_id;
        this.person_id = person_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourses_id() {
        return courses_id;
    }

    public void setCourses_id(Integer courses_id) {
        this.courses_id = courses_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "Courses_detail{" +
                "id=" + id +
                ", courses_id=" + courses_id +
                ", person_id=" + person_id +
                '}';
    }
}
