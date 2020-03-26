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
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/register")
    public String initPage(Model model, String error) {

        if (error != null) {
            System.out.println(error);
            model.addAttribute("error", "Your username or password is invalid");
        }
        model.addAttribute("user", new User());
        return "register";
    }


    //    TODO: not working add to DB, fix
    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("user") User user, Model model, BindingResult bindingResult) {

//        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!(user.getUsermail().matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
        & user.getPassword().matches("[A-Za-z\\d{8+}]")
        )) {
            model.addAttribute("error", "E-mail or password not valid");
        }
        user = userService.addUser(user);
        System.out.println(user);
//        securityService.autologin(user.getUsername(), user.getPassword());

        return "redirect:/first";
    }


}
