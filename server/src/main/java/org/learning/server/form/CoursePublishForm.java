package org.learning.server.form;

import org.hibernate.validator.constraints.Range;
import org.learning.server.entity.Course;
import org.learning.server.form.pattern.Patterns;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CoursePublishForm {
    @NotNull
    @Range(min = 0, max = 200)
    private Integer id;


    @NotNull
    @Size(min = 6, max = 128)
    private String info;
    @NotNull
    @Size(min = 6, max = 128)
    private String name;
    @NotNull
    @Size(min = 6, max = 128)
    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Course toCourse(){
        Course course=new Course();
        course.setId(getId());
        course.setInfo(getInfo());
        course.setName(getName());
        course.setPic(getPic());
        return course;
    }

}
