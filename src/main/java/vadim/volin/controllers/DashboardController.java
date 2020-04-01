package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.User;

@Controller
@SessionAttributes("user")
public class DashboardController {

    @GetMapping("/dashboard")
    public String initPage(@ModelAttribute User user, Model model) {
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
            return "redirect:/login?error";
        }
        model.addAttribute("pageName", "Task board");
        return "dashboard";
    }

}
