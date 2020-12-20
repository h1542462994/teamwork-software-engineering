package org.learning.server.controller;

import org.learning.server.entity.Course;
import org.learning.server.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * dispatcher the root views
 */
@Controller
public class MainController {

    @GetMapping("/me")
    public String meGet(Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user =(User)session.getAttribute("user");
        model.addAttribute("user", user);
        return "me";
    }
}
