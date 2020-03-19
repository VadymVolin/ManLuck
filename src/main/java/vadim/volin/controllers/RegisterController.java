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
public class RegisterController {

    @GetMapping("/register")
    public String initPage(Model model, String error) {

        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("user") User user, Model model /*, BindingResult bindingResult*/) {

        /*
        * userValidator.validate(user, bindingResult);
        * if (bindingResult.hasErrors()) {
        *   return "register";
        * }
        * userService.save(user);
        * securityService.autoLogin(user.getUsername(), user.getPassword());
        * */
        model.addAttribute("usermail", user.getUsermail());
        model.addAttribute("password", user.getPassword());
        if (user.getUsername() == null) {
            user.setUsername("LOGIN_USER");
        }
        model.addAttribute("username", user.getUsername());
        return "redirect:/first";
    }


}
