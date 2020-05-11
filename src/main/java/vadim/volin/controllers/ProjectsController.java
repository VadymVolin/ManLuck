package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import vadim.volin.model.User;

@Controller
@SessionAttributes({"user"})
public class ProjectsController {

    @GetMapping("/projects")
    public ModelAndView initPage(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        modelAndView.addObject("pageName", "Projects");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
