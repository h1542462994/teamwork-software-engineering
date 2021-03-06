package org.learning.server.form;

import org.learning.server.form.pattern.Patterns;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginForm {
    @NotNull
    @Pattern(regexp = Patterns.digitNumberUnderLine, message = "Uid仅包含数字、大小写字母和下划线")
    @Size(min = 2, max = 20)
    private String uid;
    @NotNull
    @Size(min = 6, max = 128)
    private String password;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
