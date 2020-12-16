package org.learning.server.user;

import org.junit.jupiter.api.Test;
import org.learning.server.entity.User;
import org.learning.server.form.UserLoginForm;
import org.learning.server.form.UserRegisterForm;
import org.learning.server.model.ActionResult;
import org.learning.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@SpringBootTest
public class UserTests {
    // data contracts
    private static final String uid = "SPRINGTEST";
    private static final String name = "SPRING";
    private static final String password = "123456";

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void deleteTestUser() {
        userService.delete(uid);
    }

    public ActionResult<User> addTestUser(){
        UserRegisterForm form = new UserRegisterForm();
        form.setUid(uid);
        form.setName(name);
        form.setPassword(password);
        form.setRePassword(password);
        form.setAge(4);
        form.setSex(false);
        return userService.register(form);
    }

    @Test
    @Transactional
    public void registerUser() {
        deleteTestUser();
        ActionResult<User> userResult = addTestUser();
        Assert.isTrue(!userResult.isFailed(), "注册失败");
        User user = userResult.getValue();
        Assert.notNull(user, "用户为空");
        Assert.isTrue(user.getUid().equals(uid), "用户名不为预期值：" + uid);
        Assert.isTrue(user.getPassword().equals(password), "密码不为预期值：" + password);
        deleteTestUser();
    }
}
