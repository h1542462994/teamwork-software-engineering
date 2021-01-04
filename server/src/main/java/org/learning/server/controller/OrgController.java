package org.learning.server.controller;

import net.bytebuddy.implementation.bind.annotation.Default;
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

    /**
     * TODO @page org.html
     * @return page:org.html
     */
    @GetMapping("")
    public String org(){
        return "org";
    }
}
