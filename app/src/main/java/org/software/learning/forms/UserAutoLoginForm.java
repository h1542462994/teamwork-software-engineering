package org.software.learning.forms;


import org.software.learning.forms.pattern.Patterns;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserAutoLoginForm {
    @NotNull
    @Pattern(regexp = Patterns.digitNumberUnderLine)
    @Size(min = 2, max = 20)
    private String uid;

    @NotNull
    @Pattern(regexp = Patterns.token)
    private String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
