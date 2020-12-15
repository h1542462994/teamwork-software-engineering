package main.java.com.example.controller;

import main.java.com.example.bean.Courses;
import main.java.com.example.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @RequestMapping("/courses")
    public String courses() {
        return "courses";
    }
    @RequestMapping("/courseslist")
    public String courseslist() {
        return "courseslist";
    }

    @RequestMapping("/coursesfind")
    public String login(Courses courses, Model model) {
        List<Courses> coursesList = coursesService.findbyname(courses.getName());
            model.addAttribute("coursesList", coursesList);
            return "courseslist";
    }
}
