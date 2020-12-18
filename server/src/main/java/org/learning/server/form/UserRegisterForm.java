package org.learning.server.form;

import org.hibernate.validator.constraints.Range;
import org.learning.server.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterForm extends UserLoginForm {
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @Size(min = 6, max = 128)
    private String rePassword;
    @NotNull
    @Range(min = 0, max = 200)
    private Integer age;
    @NotNull
    private Boolean sex;
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toUser() {
        User user = new User();
        user.setUid(getUid());
        user.setName(getName());
        user.setPassword(getPassword());
        user.setSex(getSex());
        user.setAge(getAge());
        user.setEmail(getEmail());
        return user;
    }
}
