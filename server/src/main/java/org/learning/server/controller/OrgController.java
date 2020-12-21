package org.learning.server.controller;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.learning.server.entity.Course;
import org.learning.server.model.Org;
import org.learning.server.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/org")
public class OrgController {
    private OrgService orgService;

    @Autowired
    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

    @GetMapping("/{id}")
    public Object orgDetailGet(@PathVariable Integer id, Model model, HttpSession session){
        Optional<Org> org = orgService.getOrg(id);
        if (org.isPresent()) {
            model.addAttribute("org", org.get());
            return "/org_detail";
        } else {
            session.setAttribute("tip", "不存在id为" + id + "的组织");
            return new RedirectView("/org");
        }
    }

    @GetMapping("/{delete}")
    public Object orgDetailDelete(@PathVariable Integer delete, Model model, HttpSession session){
        Optional<Org> org = orgService.getOrg(delete);
        if (org.isPresent()) {
            String Delete=Integer.toString(delete);
           orgService.deleteOrgs(Delete);
            return new RedirectView("/org");
        } else {
            session.setAttribute("tip", "不存在id为" + delete + "的组织");
            return new RedirectView("/org");
        }
    }


    @GetMapping("")           //finished
    public String orgGet(String error, Model model){
        model.addAttribute("orgs", orgService.getOrgs());
        return "/org";
    }
    @RequestMapping("/find")
    public String orgfind(Model model,String name) {
        List<Org> orgList = orgService.findOrgsByName(name);
        model.addAttribute("orgList", orgList);
        return "orgs";
    }

   /* @GetMapping("")
    public Object orgQuit(@PathVariable Integer id, Model model, HttpSession session ,@PathVariable Integer orgId){
        //我打算先写个好一点的，删除之后直接就重新显示
        Optional<Org> org = orgService.getOrg(id);
        boolean comeout=orgService.deleteOrgRelation(id,orgId);
        if(comeout){
            return new RedirectView("/{id}");
        }
        else{
            return "/org";
        }

    }*/

}
