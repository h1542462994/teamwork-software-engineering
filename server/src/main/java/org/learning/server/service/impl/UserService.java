package org.learning.server.service.impl;

import org.learning.server.entity.User;
import org.learning.server.form.UserLoginForm;
import org.learning.server.form.UserRegisterForm;
import org.learning.server.model.ActionResult;
import org.learning.server.model.common.Response;
import org.learning.server.model.common.Responses;
import org.learning.server.repository.UserRepository;
import org.learning.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response<User> register(UserRegisterForm user) {

        if (!user.getPassword().equals(user.getRePassword())) {
            return Responses.fail("密码与确认密码不相等");
        }

        User formUser = user.toUser();
        // get the user in db
        Optional<User> dbUser = userRepository.findByUid(user.getUid());
        if (dbUser.isPresent()) {
            return Responses.fail("该用户已经注册");
        }

        userRepository.save(formUser);

        return Responses.ok(formUser);
    }

    @Override
    public Response<User> login(UserLoginForm user) {

        Optional<User> dbUser = userRepository.findByUid(user.getUid());
        /*if (dbUser.isEmpty()) {
            return Responses.fail("该账号不存在");
        }*/

        if (!dbUser.get().getPassword().equals(user.getPassword())){
            return Responses.fail("密码错误");
        }

        return Responses.ok(dbUser.get());
    }

    @Override
    public boolean delete(String uid) {
        return userRepository.deleteByUid(uid) > 0;
    }
}
