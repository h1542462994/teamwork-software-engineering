package org.learning.server.service;

import org.learning.server.entity.User;
import org.learning.server.form.UserLoginForm;
import org.learning.server.form.UserRegisterForm;
import org.learning.server.model.ActionResult;
import org.learning.server.model.common.Response;
import org.learning.server.model.common.Responses;

public interface IUserService {
    ActionResult<User> register(UserRegisterForm user);
    Response<User> login(UserLoginForm user);
    boolean delete(String uid);
}
