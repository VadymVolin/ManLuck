package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import vadim.volin.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("user")
public class FirstPageController {

    @GetMapping("/first")
    public String initPage(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
        if (request.getRequestURI()
                .endsWith("clear")) {
            request.getSession().invalidate();
            return "login";
        }
        model.addAttribute("user", user);
        return "first-page";
    }
}

