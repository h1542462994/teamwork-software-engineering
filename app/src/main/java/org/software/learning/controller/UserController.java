package org.software.learning.controller;

import org.software.learning.exception.HttpStatusException;
import org.software.learning.forms.UserAutoLoginForm;
import org.software.learning.forms.UserForm;
import org.software.learning.forms.UserLoginForm;
import org.software.learning.model.common.ApiResponse;
import org.software.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ApiResponse<?> register(@Validated UserForm user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse<?> login(@Validated UserLoginForm user) throws HttpStatusException {
        return userService.login(user);
    }

    @PostMapping("/auto_login")
    @ResponseBody
    public ApiResponse<?> autoLogin(@Validated UserAutoLoginForm userAutoLoginForm) throws HttpStatusException {
        return userService.autoLogin(userAutoLoginForm);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
