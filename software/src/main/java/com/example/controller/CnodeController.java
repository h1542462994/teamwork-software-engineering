package com.example.controller;

import com.example.bean.Cnode;
import com.example.bean.Courses;
import com.example.service.CnodeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CnodeController {

    @Autowired
    private CnodeService cnodeService;

    @RequestMapping("/cnodelist")
    public String cnodelist() {
        return "cnodelist";
    }

    @RequestMapping("/cnodefind")
    public String cnodefind(Courses courses, Model model) {
        List<Cnode> cnodeList = cnodeService.findbyid(courses.getId());
        model.addAttribute("cnodeList", cnodeList);
        model.addAttribute("coursesname", courses.getName());
        return "cnodelist";
    }

    @RequestMapping("/cnodeplay")
    public String cnodeplay(Cnode cnode, ModelMap model) {
        List<Cnode> cnodeList = cnodeService.findbycoursesidAndnodeid(cnode.getCoursesid(), cnode.getNodeid());
        if (cnodeList.get(0).getType().equals("video")) {
            model.addAttribute("path", cnodeList.get(0).getPath());
            return "videoplay";
        } else if (cnodeList.get(0).getType().equals("audio")) {
            model.addAttribute("path", cnodeList.get(0).getPath());
            return "audioplay";
        } else {
            return "pptplay";
        }
    }
}
