package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import vadim.volin.model.User;
import vadim.volin.services.UserService;
import vadim.volin.validate.UserValidator;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @ModelAttribute
    public User createUser() {
        return new User();
    }

    @GetMapping("/register")
    public String initPage(Model model, String error, HttpSession httpSession, SessionStatus sessionStatus) {
        if (httpSession.getAttribute("user") != null
                && !sessionStatus.isComplete()
                && !httpSession.isNew()) {
            sessionStatus.setComplete();
            httpSession.invalidate();
            return "redirect:/register";
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("user") User user, Model model, BindingResult bindingResult) {
        User existing = userService.getByUserMail(user.getUsermail());

        if (existing != null) {
            bindingResult.rejectValue("usermail", null, "There is already an account registered with that email");
            return "redirect:/register";
        }

        if (!(user.getUsermail().matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
                & user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,34}$")
        )) {
            bindingResult.rejectValue("password", "E-mail or password not valid");

            return "redirect:/register";
        }

        if (!user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,34}$")) {
            bindingResult.rejectValue("password", "E-mail or password not valid");
            return "redirect:/register";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("password", "E-mail or password not valid");
            return "redirect:/register";
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.addUser(user);
        return "redirect:/first";
    }


}
