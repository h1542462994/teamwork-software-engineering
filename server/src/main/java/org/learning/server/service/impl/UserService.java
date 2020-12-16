package org.learning.server.service.impl;

import org.learning.server.entity.User;
import org.learning.server.form.UserLoginForm;
import org.learning.server.form.UserRegisterForm;
import org.learning.server.model.ActionResult;
import org.learning.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements org.learning.server.service.UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ActionResult<User> register(UserRegisterForm user) {
        ActionResult<User> actionResult = new ActionResult<>();

        if (!user.getPassword().equals(user.getRePassword())) {
            return actionResult.error("确认密码与密码不相等");
        }

        User formUser = user.toUser();
        // get the user in db
        Optional<User> dbUser = userRepository.findByUid(user.getUid());
        if (dbUser.isPresent()) {
            return actionResult.error("该用户已经注册");
        }

        userRepository.save(formUser);

        return actionResult.value(formUser).success();
    }

    @Override
    public ActionResult<User> login(UserLoginForm user) {
        ActionResult<User> actionResult = new ActionResult<>();

        Optional<User> dbUser = userRepository.findByUid(user.getUid());
        if (dbUser.isEmpty()) {
            return actionResult.error("该账户不存在");
        }

        if (!dbUser.get().getPassword().equals(user.getPassword())){
            return actionResult.error("密码错误");
        }

        return actionResult.value(dbUser.get()).success();
    }

    @Override
    public boolean delete(String uid) {
        return userRepository.deleteByUid(uid) > 0;
    }
}
