package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import vadim.volin.model.User;
import vadim.volin.services.UserService;
import vadim.volin.validate.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/login")
    public String initPage(Model model, String error, String logout, HttpSession httpSession, SessionStatus sessionStatus, HttpServletRequest request) {
        if (httpSession.getAttribute("user") != null || !httpSession.isNew()) {
            httpSession.invalidate();
        }
        if (error != null) {
            model.addAttribute("error", "Please, log in system!");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login*")
    public String loginProcess(@ModelAttribute User user, Model model, BindingResult bindingResult, HttpSession httpSession) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        user = userService.getByUserMail(user.getUsermail());
        model.addAttribute("user", user);
        httpSession.setMaxInactiveInterval(24 * 60 * 60 * 30);
        return "redirect:/first";
    }

    @GetMapping({"/logout"})
    public String logoutProcess(HttpSession httpSession, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        httpSession.invalidate();
        return "redirect:/login?logout=ok";
    }

}
