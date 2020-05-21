package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vadim.volin.model.Project;
import vadim.volin.model.User;
import vadim.volin.services.ProjectService;
import vadim.volin.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@SessionAttributes({"user"})
public class ProjectsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

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
        Project project = new Project(project_name);
        user.getProjects().add(project);
        User update = userService.editUser(user);
        project = update.getProjects().stream().filter(project1 -> project1.getProject_name().equals(project_name)).findFirst().get();
        model.addAttribute("user", update);
        return new ResponseEntity(String.valueOf(project.getProject_id()), HttpStatus.OK);
    }

    @GetMapping(value = "/projects/{user_id}/{project_id}")
    public String loadProjectData(@PathVariable String user_id, @PathVariable String project_id, @ModelAttribute User user, Model model) {
        if (user == null || user.getRoles() == null || !user.getRoles().contains("ROLE_USER")
                || user_id == null) {
            return "redirect:/login";
        }
        if (project_id == null) {
            return "redirect:/projects";
        }
        boolean hasProject = false;
        Project project = null;
        List<Project> projectList = user.getProjects();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getProject_id().equals(Integer.parseInt(project_id))) {
                hasProject = true;
                project = projectList.get(i);
                model.addAttribute("pageName", project.getProject_name());
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

    @PostMapping(value = "/projects/remove", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> removeProject(
            @RequestParam(name = "projectId", defaultValue = "") String projectId, @ModelAttribute User user, Model model
    ) {
        if (user == null || projectId == null || projectId.equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Project project = null;
        for (int i = 0; i < user.getProjects().size(); i++) {
            if (user.getProjects().get(i).getProject_id().equals(Integer.parseInt(projectId))) {
                project = user.getProjects().get(i);
                user.getProjects().remove(project);
                User update = userService.editUser(user);
                if (project.getTeam().isEmpty()) {
                    projectService.removeProject(project);
                }
                model.addAttribute("user", update);
                break;
            }
        }
        return new ResponseEntity("Deleted!", HttpStatus.OK);
    }


    // TODO: add user to project
    @PostMapping(value = "/projects/{id}/add/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<String> addUserToProject(
            @RequestParam(name = "usermail", defaultValue = "") String usermail, @PathVariable String id, @ModelAttribute User user, Model model
    ) {
        if (user == null || usermail == null || usermail.equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User futureUser = userService.getByUserMail(usermail);
        if (futureUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        for (int i = 0; i < user.getProjects().size(); i++) {
            if (user.getProjects().get(i).getProject_id().equals(Integer.parseInt(id))) {
                if (user.getProjects().get(i).getTeam().contains(futureUser)) {
                    return ResponseEntity.badRequest().body("User already added!");
                } else {
                    user.getProjects().get(i).getTeam().add(futureUser);
                    User update = userService.editUser(user);
                    model.addAttribute("user", update);
                    System.out.println(update.getProjects().get(i).getTeam());
                    return ResponseEntity.ok("User already added!");
                }
            }
        }
        return ResponseEntity.badRequest().body("Try later!");
    }

    //    TODO: add file to project
    @PostMapping("/projects/{id}/upload/file")
    @ResponseBody
    public ResponseEntity<?> uploadProjectFile(
            @RequestBody MultipartFile file, @PathVariable String id, @ModelAttribute User user, Model model
    ) {
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
            return ResponseEntity.badRequest().body("Try later");
        }
        if (file.isEmpty()) {
            model.addAttribute("message", "Please, choose file!");
            return ResponseEntity.badRequest().body("Please, select image for uploading!");
        }
        try {
            Project project = user.getProjects().stream()
                    .filter(project1 -> (
                            project1.getProject_id().equals(Integer.valueOf(id))
                    ))
                    .findFirst()
                    .get();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(
                    "/home/vadim/Documents/Spring/springMVC-courses/target/springMVC-courses/manluck_data/projects/"
                            + id + "/"
                            + file.getOriginalFilename()
            );
            Files.createDirectories(path.getParent());

            Files.write(path, bytes);
//            user.setUser_img("/manluck_data/user_img/" + filename);
            userService.editUser(user);
            model.addAttribute("user", user);
        } catch (IOException e) {
//            model.addAttribute("message", "Server error, please, try again!");
            e.printStackTrace();
            return new ResponseEntity("Please, select image for uploading!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Successfully upload!");
    }

}
