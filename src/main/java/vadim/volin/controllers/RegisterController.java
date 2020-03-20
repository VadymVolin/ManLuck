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

@Controller
@SessionAttributes("user")
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

//    @ModelAttribute("user")
//    private User getMoney() {
//        return new User(); //or however you create a default
//    }

    @GetMapping("/register")
    public String initPage(Model model, String error) {

        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }
        model.addAttribute("user", new User());
        return "register";
    }


//    TODO: not working add to DB, fix
    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("user") User user, Model model/*, BindingResult bindingResult*/) {

//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
        System.out.println(userService.addUser(user));
//        securityService.autologin(user.getUsername(), user.getPassword());
        model.addAttribute("usermail", user.getUsermail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userphone", user.getUserphone());
        model.addAttribute("country", user.getCountry());
        model.addAttribute("company", user.getCompany());
        model.addAttribute("city", user.getCity());
        model.addAttribute("position", user.getPosition());
        return "redirect:/first";
    }


}
