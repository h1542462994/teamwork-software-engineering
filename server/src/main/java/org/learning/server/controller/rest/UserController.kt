package org.learning.server.controller.rest

import org.learning.server.entity.User
import org.learning.server.form.UserLoginForm
import org.learning.server.form.UserRegisterForm
import org.learning.server.model.annotation.NoLogin
import org.learning.server.model.common.Response
import org.learning.server.model.common.ResponseTokens
import org.learning.server.model.common.Responses
import org.learning.server.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

/**
 * 与用户有关的控制器
 */
@RestController
@RequestMapping("/api/user")
class UserController {
    @Autowired
    lateinit var userService: UserService
    private val user: String = "user"

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @NoLogin
    fun login(@Valid userLoginForm: UserLoginForm, request: HttpServletRequest): Response<User> {
        val response = userService.login(userLoginForm)
        if (response.code == ResponseTokens.ok.code){
            // 在会话中添加用户
            request.session.setAttribute(user, response.data)
        }
        return userService.login(userLoginForm)
    }

    /**
     * 获取当前用户的状态
     */
    @PostMapping("/state")
    fun state(request: HttpServletRequest) : Response<User> {
        val user = request.session.getAttribute(user)
        return if (user == null){
            Responses.fail("你还没有登录")
        } else {
            Responses.ok(user as User)
        }
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    fun logout(request: HttpServletRequest) : Response<Any> {
        request.session.removeAttribute(user);
        return Responses.ok();
    }

    /**
     * 注册用户
     */
    @PostMapping("/register")
    @NoLogin
    fun register(@Valid userRegisterForm: UserRegisterForm): Response<User> {
        return userService.register(userRegisterForm);
    }

}