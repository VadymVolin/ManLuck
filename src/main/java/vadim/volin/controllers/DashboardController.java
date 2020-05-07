package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("user")
public class DashboardController {

    @GetMapping("/dashboard")
    public String initPage(@ModelAttribute User user, Model model) {
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
//            model.addAttribute("error", "Please, log in system!");
            return "redirect:/login";
        }
        model.addAttribute("pageName", "Create your plan");
        return "dashboard";
    }



}
