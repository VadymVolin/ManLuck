package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@SessionAttributes("user")
public class DashboardController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String initPage(@ModelAttribute User user, Model model) {
        if (user.getRoles() == null || user == null || !user.getRoles().contains("ROLE_USER")) {
            model.addAttribute("error", "Please, log in system!");
            return "redirect:/login";
        }

        model.addAttribute("pageName", "Create your plan");
//
//        String json = user.getUserTasksJson();
//        if (json != null && !json.equals("") && !json.equals("[]")) {
//            model.addAttribute("jsonTasks", json);
//        }

        return "dashboard";
    }

    @PostMapping(value = "/dashboard/user/tasks")
    @ResponseBody
    public ResponseEntity<String> updateDashboardData(@RequestBody String jsonTasks, Model model) {
        User user = (User) model.getAttribute("user");
        if (user != null && jsonTasks != null) {
            if (!jsonTasks.equals(user.getUserTasksJson())) {
                user.setUserTasksJson(jsonTasks);
                userService.editUser(user);
                return new ResponseEntity<String>("Success", HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Updated",HttpStatus.NOT_MODIFIED);
    }

}
