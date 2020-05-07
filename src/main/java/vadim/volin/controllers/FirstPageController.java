package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.User;

@Controller
@SessionAttributes("user")
public class FirstPageController {

    @GetMapping("/first")
    public String initPage(@ModelAttribute("user") User user, Model model) {
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
//            model.addAttribute("error", "Please, log in system!");
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "first-page";
    }
}

