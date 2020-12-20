package org.learning.server.controller;

import org.learning.server.entity.User;
import org.learning.server.form.UserLoginForm;
import org.learning.server.form.UserRegisterForm;
import org.learning.server.model.ActionResult;
import org.learning.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;


    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("user", new UserRegisterForm());
        return "register";
    }

    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("user", new UserLoginForm());
        return "login";
    }

    @PostMapping("/register")
    public Object registerPost(@Valid @ModelAttribute(value = "user") UserRegisterForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return  "register";
        }

        ActionResult<User> actionResult = userService.register(user);
        if (actionResult.isFailed()) {
            return new ModelAndView("register")
                    .addObject("user", user)
                    .addObject("result", actionResult);
        }

        return new ModelAndView("registerSuccess")
                .addObject("user", actionResult.getValue());
    }

    @PostMapping("/login")
    public Object loginPost(@Valid @ModelAttribute(value = "user") UserLoginForm user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        ActionResult<User> actionResult = userService.login(user);
        if (actionResult.isFailed()) {
            return new ModelAndView("login")
                    .addObject("user", user)
                    .addObject("result", actionResult);
        }

        session.setAttribute("user", actionResult.getValue());

        return new RedirectView("/");
    }
}
