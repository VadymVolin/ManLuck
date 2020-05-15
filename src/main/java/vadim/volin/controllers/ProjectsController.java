package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vadim.volin.model.Project;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

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
    @PostMapping(value = "/projects/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> addProject(@RequestParam(name = "project_name", defaultValue = "") String project_name, @ModelAttribute User user, Model model) {
        System.out.println("this");
        if (user == null || project_name == null || project_name.equals("")) {
            System.out.println(user);
            System.out.println(project_name);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        System.out.println(project_name);
        System.out.println(user);
        user.getProjects().add(new Project(project_name));
        User update = userService.editUser(user);
        model.addAttribute("user", update);
        return new ResponseEntity("Project created", HttpStatus.OK);
    }



}
