package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.User;

@Controller
@SessionAttributes("user")
public class LoginController {

    @GetMapping("/login")
    public String initPage(Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute User user, Model model/* , BindingResult bindingResult */) {
        model.addAttribute("usermail", user.getUsermail());
        model.addAttribute("password", user.getPassword());
        if (user.getUsername() == null) {
            user.setUsername("LOGIN_USER");
        }
        model.addAttribute("username", user.getUsername());
        return "redirect:/first";
    }

}
