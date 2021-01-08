package org.learning.server.controller;

import org.learning.server.model.annotation.NoLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@NoLogin
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required = false, defaultValue = "World")String name, Model model) {

        model.addAttribute("name", name);
        return "greeting";
    }
}
