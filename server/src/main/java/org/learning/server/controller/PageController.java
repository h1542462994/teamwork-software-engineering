package org.learning.server.controller;

import org.learning.server.model.annotation.NoLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * dispatcher the root views
 */
@Controller
@RequestMapping("/")
@NoLogin
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

    /**
     * TODO @page:org_detail.html
     * @return page:org_detail.html
     */
    @GetMapping("/org/{id}")
    public String orgDetail(@PathVariable int id) { return "org_detail"; }
}
