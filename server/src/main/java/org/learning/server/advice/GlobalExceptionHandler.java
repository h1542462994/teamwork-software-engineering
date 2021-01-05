package org.learning.server.advice;

import org.learning.server.entity.User;
import org.learning.server.form.UserLoginForm;
import org.learning.server.model.ActionResult;
import org.learning.server.model.common.Responses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

/**
 * To handle the exception and return a ModelAndView.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Object loginExceptionHandler(HttpServletRequest request, LoginException e){
        if (request.getRequestURI().startsWith("/api")) {
            return Responses.fail("当前未登录");
        } else {
            return new ModelAndView("/login");
        }
    }
}
