package com.example.controller;

import com.example.bean.User;
import com.example.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/loginsuccess")
    public String loginsuccess(){
        return "loginsuccess";
    }
    @RequestMapping("/loginfail")
    public String loginfail(){
        return "loginfail";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(User user) {
        if (userService.save(user)) return "注册成功 !";
        else return "注册失败";
    }

    @RequestMapping("/find")
    public String login(User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //List<User> userList =userService.login(user.getId(),user.getName(),user.getPassword());
        List<User> userList = userService.login(user.getName(), user.getPassword());
        if (userList.size() > 0) {
            model.addAttribute("userList", userList);
            session.setAttribute("user", userList.get(0));
            return "loginsuccess";
        } else return "loginfail";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        if (userService.delete(user.getId()))return "注销成功！";
        else return"出错了！";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.removeAttribute("user");
        return "login";
    }
}
