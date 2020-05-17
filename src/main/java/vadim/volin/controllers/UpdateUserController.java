package vadim.volin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@SessionAttributes("user")
public class UpdateUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update/user/img")
    @ResponseBody
    public ResponseEntity<String> handleUploadImage(@RequestBody MultipartFile file, @ModelAttribute User user, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please, choose file!");
            return new ResponseEntity("Please, select image for uploading!", HttpStatus.OK);
        }
        try {
            byte[] bytes = file.getBytes();
            String filename = user.getUsername().toLowerCase().replaceAll("\\s+", "") + ".png";
            Path path = Paths.get("/home/vadim/Documents/Spring/springMVC-courses/target/springMVC-courses/manluck_data/user_img/"
                    + filename);
            Files.write(path, bytes);
            user.setUser_img("manluck_data/user_img/" + filename);
            userService.editUser(user);
            model.addAttribute("user", user);
        } catch (IOException e) {
            model.addAttribute("message", "Server error, please, try again!");
            return new ResponseEntity("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Successfully upload!", HttpStatus.OK);
    }

    @PostMapping(value = "/update/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> updateUserInfo(@ModelAttribute User user, Model model) {
        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User update = userService.editUser(user);
        model.addAttribute("user", update);
        return new ResponseEntity("Successfully upload!", HttpStatus.OK);
    }

//    @ResponseBody
    @RequestMapping(value = "/update/deactivate", method = RequestMethod.POST)
    public String deactivateUser(@ModelAttribute User user) {
        if (user == null) {
            return "Error on deactivate";
        }
        user.setActive(false);
        userService.editUser(user);
        return "redirect:/login";
    }

}
