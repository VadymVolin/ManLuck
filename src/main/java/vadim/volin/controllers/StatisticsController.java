package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.Project;
import vadim.volin.model.User;
import vadim.volin.services.ProjectService;
import vadim.volin.util.ProjectUtils;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Controller
@SessionAttributes({"user"})
public class StatisticsController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/statistics")
    public String getStatisticPage(@ModelAttribute User user, Model model) {
        if (user == null || user.getRoles() == null || !user.getRoles().contains("ROLE_USER")) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("pageName", "Statistics");

        return "statistics";
    }

    @GetMapping(value = "/statistics/{project_id}")
    public String getProjectStatistic(@PathVariable String project_id, @ModelAttribute User user, Model model) {
        if (user == null || user.getRoles() == null || !user.getRoles().contains("ROLE_USER")) {
            return "redirect:/login";
        }
        if (project_id == null || project_id.equals("")) {
            return "redirect:/projects";
        }

        Project project = projectService.getProject(Integer.parseInt(project_id));
        if (project == null) {
            return "redirect:/projects";
        }

        int tasksCount = 0;
        Map<Integer, String> taskMap = new TreeMap<>();
        try {
            tasksCount = ProjectUtils.getTaskCount(project.getProject_tasks());
            taskMap = ProjectUtils.getTasksData(project.getProject_tasks());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        model.addAttribute("pageName", project.getProject_name());
        model.addAttribute("project", project);
        model.addAttribute("tasksCount", tasksCount);
        model.addAttribute("taskMap", taskMap);
        model.addAttribute("user", user);
        return "project-statistics";
    }

}
