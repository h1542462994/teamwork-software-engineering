package org.learning.server.controller;

import org.learning.server.entity.Course;
import org.learning.server.entity.CourseNode;
import org.learning.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("")
    public String courseGet(Model model) {
        List<Course> courseList = courseService.getCourses();
        model.addAttribute("courseList", courseList);
        return "courses";
    }
    @RequestMapping("/find")
    public String coursefind(Model model,String name) {
        List<Course> courseList = courseService.findCoursesByName(name);
        model.addAttribute("courseList", courseList);
        return "courses";
    }
    @GetMapping("/{id}")
    public String cnodefind(@PathVariable Integer id,Model model, String name) {
        List<CourseNode> cnodeList = courseService.findCnodeById(id);
        model.addAttribute("cnodeList", cnodeList);
        return "coursesnode";
    }
}
