package org.learning.server.service;

import org.learning.server.entity.User;
import org.learning.server.form.UserRegisterForm;
import org.learning.server.model.ActionResult;

public interface UserService {
    ActionResult<User> register(UserRegisterForm user);
}
