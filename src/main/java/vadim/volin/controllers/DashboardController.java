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
        model.addAttribute("pageName", "Task board");
        return "dashboard";
    }

}
