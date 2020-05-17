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
        return "projects";
    }

    @PostMapping(value = "/projects/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> addProject(@RequestParam(name = "project_name", defaultValue = "") String project_name, @ModelAttribute User user, Model model) {
        if (user == null || project_name == null || project_name.equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        user.getProjects().add(new Project(project_name));
        User update = userService.editUser(user);
        model.addAttribute("user", update);
        return new ResponseEntity("Project created", HttpStatus.OK);
    }

    @GetMapping(value = "/projects/{userName}/{projectName}")
    public String loadProjectData(@PathVariable String userName, @PathVariable String projectName, @ModelAttribute User user, Model model) {
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")
        || userName == null || projectName == null) {
            return "redirect:/login";
        }
        boolean hasProject = false;
        Project project = null;
        List<Project> projectList = user.getProjects();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getProject_name().equals(projectName)) {
                hasProject = true;
                project = projectList.get(i);
                model.addAttribute("project", project);
                break;
            }
        }
        if (hasProject) {
            return "project-info";
        } else {
            return "redirect:/projects";
        }
    }

}
