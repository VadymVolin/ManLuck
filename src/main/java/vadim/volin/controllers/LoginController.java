package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.User;
import vadim.volin.services.UserService;
import vadim.volin.validate.UserValidator;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

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
    public String loginProcess(@ModelAttribute User user, Model model, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }

        User validUser = userService.getByUserMail(user.getUsermail());
        if (validUser == null) {
            model.addAttribute("error", "User not found");
            return "login";
        }
        model.addAttribute("user", validUser);
        return "redirect:/first";
    }

}
