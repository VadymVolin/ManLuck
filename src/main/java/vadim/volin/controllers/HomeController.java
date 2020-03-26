package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.User;

@Controller
@SessionAttributes("user")
public class HomeController {

    @GetMapping("/home")
    public String sayHello(@ModelAttribute User user, Model model) {
        model.addAttribute("pageName", "Home");
        if (user.getUsername() == null) {
            user.setUsername("LOGIN_USER");
        }
        model.addAttribute("username", user.getUsername());
        return "home";
    }



}
