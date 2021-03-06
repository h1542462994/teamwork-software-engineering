package org.learning.server.advice;

import org.learning.server.entity.User;
import org.learning.server.exception.NoAllowedException;
import org.learning.server.form.UserLoginForm;
import org.learning.server.model.ActionResult;
import org.learning.server.model.common.ResponseToken;
import org.learning.server.model.common.ResponseTokens;
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
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    Object loginExceptionHandler(HttpServletRequest request, LoginException e){
        if (request.getRequestURI().startsWith("/api")) {
            return Responses.withToken(ResponseTokens.User.INSTANCE.getNoLogin(), e.getMessage());
        } else {
            return new ModelAndView("/login");
        }
    }

    @ExceptionHandler(NoAllowedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    Object loginExceptionHandler(HttpServletRequest request, NoAllowedException e){
        if (request.getRequestURI().startsWith("/api")) {
            return Responses.withToken(ResponseTokens.User.INSTANCE.getForbidden(), e.getMessage());
        } else {
            return new ModelAndView("/");
        }
    }
}
