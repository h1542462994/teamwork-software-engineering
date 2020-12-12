package org.software.learning.common;

import org.software.learning.forms.UserForm;
import org.software.learning.forms.UserLoginForm;
import org.software.learning.model.entity.User;
import org.software.learning.model.entity.UserToken;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class UserTool {
    public static User toUserNormal(UserLoginForm userForm) {
        if (userForm == null){
            return null;
        } else if (userForm instanceof UserForm) {
            return new User(userForm.getUid(), User.NORMAL, User.GROUP_NORMAL, ((UserForm) userForm).getName(), userForm.getPassword(), ((UserForm) userForm).getEmail());
        } else {
            return new User(userForm.getUid(), userForm.getPassword());
        }
    }

    public static UserToken generateToken(User user) {
        UserToken token = new UserToken();
        token.setUid(user.getUid());
        token.setToken(UUID.randomUUID().toString());
        Instant instant = Instant.now();
        token.setCreated(Timestamp.from(instant));
        instant = instant.plus(7, ChronoUnit.DAYS);
        token.setExpired(Timestamp.from(instant));
        return token;
    }
}
