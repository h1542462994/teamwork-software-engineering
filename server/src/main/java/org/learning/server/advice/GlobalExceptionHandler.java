package org.learning.server.advice;

import org.learning.server.entity.User;
import org.learning.server.form.UserLoginForm;
import org.learning.server.model.ActionResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    ModelAndView loginExceptionHandler(HttpServletRequest request, LoginException e){
        return new ModelAndView("/login")
                .addObject("user", new UserLoginForm())
                .addObject("result", new ActionResult<User>().error("您还没有登录"));
    }
}
