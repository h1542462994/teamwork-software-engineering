package org.learning.server.controller;

import org.learning.server.entity.Course;
import org.learning.server.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * dispatcher the root views
 */
@Controller
@RequestMapping("/")
public class MainController {

    /**
     * TODO @page:me.html
     * 跳转到主页
     * @return page:me.html
     */
    @GetMapping("/me")
    public String me() { return "me"; }

    /**
     * TODO @page:template.html
     * 跳转到模板页
     * @return page:template.html
     */
    @GetMapping("/template")
    public String template(){
        return "template";
    }

    /**
     * TODO @page:login.html
     * @return page:login.html
     */
    @GetMapping("/user/login")
    public String login() { return "login"; }
}
