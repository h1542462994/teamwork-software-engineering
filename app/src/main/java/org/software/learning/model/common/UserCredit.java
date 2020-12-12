package org.software.learning.model.common;

import org.software.learning.model.entity.User;
import org.software.learning.model.entity.UserToken;

public class UserCredit {
    private User user;
    private UserToken token;

    public UserCredit(User user, UserToken token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserToken getToken() {
        return token;
    }

    public void setToken(UserToken token) {
        this.token = token;
    }
}
