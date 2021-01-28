package org.learning.server.interceptor

import org.learning.server.common.SessionHelper
import org.learning.server.model.annotation.NoLogin
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import javax.security.auth.login.LoginException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginInterceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        if (handler is HandlerMethod && handler.beanType.getAnnotation(NoLogin::class.java) == null) {
            if (handler.beanType == BasicErrorController::class.java) {
                return true
            }

            val user = SessionHelper.of(request).user()

            return when {
                handler.method.isAnnotationPresent(NoLogin::class.java) -> {
                    true
                }
                user == null -> {
                    throw LoginException("当前未登录")
                }
                else -> {
                    true
                }
            }
        }
        return true
    }
}