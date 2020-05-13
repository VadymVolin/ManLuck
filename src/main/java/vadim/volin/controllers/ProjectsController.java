package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import vadim.volin.model.Project;
import vadim.volin.model.ProjectRole;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user"})
public class ProjectsController {

    @Autowired
    private UserService userService;

    @GetMapping("/projects")
    public String initPage(@ModelAttribute User user, Model model) {
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
            return "redirect:/login";
        }

        model.addAttribute("pageName", "Projects");
        model.addAttribute("user", user);
//        model.addAttribute("projects", user.getProjects());
        return "projects";
    }

//    TODO: Create update project
    @PostMapping(name = "/project/add/new", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> addProject(@ModelAttribute Project project, @ModelAttribute User user, Model model) {
        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User update = userService.editUser(user);
        model.addAttribute("user", update);
        return new ResponseEntity("Successfully upload!", HttpStatus.OK);
    }


}
