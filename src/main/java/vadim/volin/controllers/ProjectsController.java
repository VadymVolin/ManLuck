package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
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
    public ModelAndView initPage(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        Project project1 = new Project("ManLuck-Project");
        Project project2 = new Project("ManLuck-Project");
        Project project3 = new Project("ManLuck-Project");

        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);

        System.out.println(userService);
        List<ProjectRole> p = new ArrayList<>();
        p.add(new ProjectRole("MANAGER"));
        System.out.println(user);
        userService.editUser(user);
        System.out.println(user);

        modelAndView.addObject("pageName", "Projects");
        modelAndView.addObject("user", user);
        modelAndView.addObject("projects", projects);
        return modelAndView;
    }

}
