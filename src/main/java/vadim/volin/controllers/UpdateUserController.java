package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("user")
@RequestMapping
public class UpdateUserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/update/user", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String updateUserInfo(@ModelAttribute User user, Model model) {
        if (user == null) {
            return "";
        }
        System.out.println(user);
        User update = userService.editUser(user);
        model.addAttribute("user", user);
        return "Data update";
    }

    @ResponseBody
    @RequestMapping(value = "/update/deactivate")
    public String deactivateUser(@ModelAttribute User user) {
        if (user == null) {
            return "redirect:/login?error=User%not%found!";
        }
        user.setActive(false);
        userService.editUser(user);
        return "redirect:/login?message=Account%has%been%deactivate";
    }

}
