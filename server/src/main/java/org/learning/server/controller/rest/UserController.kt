package org.learning.server.controller.rest

import org.learning.server.entity.User
import org.learning.server.form.UserLoginForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.ResponseTokens
import org.learning.server.model.common.Responses
import org.learning.server.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController {
    @Autowired
    lateinit var IUserService: IUserService
    private val user: String = "user"

    /**
     * 用户登录
     */
    @PostMapping("/login")
    fun login(@Valid userLoginForm: UserLoginForm, request: HttpServletRequest): Response<User> {
        val response = IUserService.login(userLoginForm)
        if (response.code == ResponseTokens.ok.code){
            // 在会话中添加用户
            request.session.setAttribute(user, response.data)
        }
        return IUserService.login(userLoginForm)
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
    fun logout() : Response<Any> {
        TODO("等待实现")
    }

    /**
     * 注册用户
     */
    @PostMapping("/register")
    fun register(): Response<Any> {
        TODO("等待实现")
    }

}