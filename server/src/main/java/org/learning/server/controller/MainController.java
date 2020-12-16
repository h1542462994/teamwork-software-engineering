package org.learning.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * dispatcher the root views
 */
@Controller
public class MainController {
    @GetMapping("/courses")
    public String courseGet(Model model) {
        return "courses";
    }

    @GetMapping("/org")
    public String orgGet(Model model) {
        return "org";
    }

    @GetMapping("/me")
    public String meGet(Model model) {
        return "me";
    }
}
