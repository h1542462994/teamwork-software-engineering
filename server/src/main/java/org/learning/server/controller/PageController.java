package org.learning.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * dispatcher the root views
 */
@Controller
@RequestMapping("/")
public class PageController {

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
    @GetMapping("/login")
    public String login() { return "login"; }

    /**
     * TODO @page:org.html
     * @return page:org.html
     */
    @GetMapping("/org")
    public String org() { return "org"; }
}
