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
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @ModelAttribute
    public User createUser() {
        return new User();
    }

    @GetMapping("/login")
    public String initPage(Model model, String error, String logout, HttpSession httpSession, SessionStatus sessionStatus) {
        if (httpSession.getAttribute("user") != null && !httpSession.isNew()) {
            sessionStatus.setComplete();
            httpSession.invalidate();
            return "redirect:/login";
        }

        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid.");
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
            System.out.println("has error");
            return "login";
        }
        User validUser = userService.getByUserMail(user.getUsermail());
        model.addAttribute("user", validUser);
        return "redirect:/first";
    }

    @GetMapping({"/logout"})
    public String logoutProcess() {
        return "redirect:/login?logout";
    }

}
