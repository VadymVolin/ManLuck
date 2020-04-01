package vadim.volin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import vadim.volin.model.User;

@Controller
@SessionAttributes("user")
public class FirstPageController {

    @GetMapping("/first")
    public ModelAndView initPage(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("first-page");
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("usermail", user.getUsermail());
        return modelAndView;
    }

}

