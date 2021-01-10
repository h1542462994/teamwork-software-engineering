package org.learning.server.form;

import org.hibernate.validator.constraints.Range;
import org.learning.server.common.TimeStampHelper;
import org.learning.server.entity.Course;
import org.learning.server.entity.User;
import org.learning.server.form.pattern.Patterns;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CourseForm {

    private Integer id;

    @NotNull
    @Size(max = 128)
    private String info;
    @NotNull
    @Size(max = 128)
    private String name;
    @NotNull
    @Size(max = 128)
    private String pic;

    /**
     * 注意，前端对应的数据项为public
     */
    @NotNull
    private Boolean isPublic;

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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Course toCourse(User user){
        Course course = new Course();
        course.setInfo(getInfo());
        course.setName(getName());
        course.setPic(getPic());
        course.setPublic(isPublic);
        course.setOwner(user);
        course.setCreateTime(TimeStampHelper.INSTANCE.now());
        course.setEditTime(TimeStampHelper.INSTANCE.now());
        return course;
    }


}
