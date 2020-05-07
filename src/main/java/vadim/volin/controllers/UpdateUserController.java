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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@SessionAttributes("user")
public class UpdateUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update/user/img")
    @ResponseBody
    public ResponseEntity<String> handleUploadImage(@RequestBody MultipartFile file, @ModelAttribute User user, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please, choose file!");
            return new ResponseEntity("Please, select image for uploading!", HttpStatus.OK);
        }
        try {
            byte[] bytes = file.getBytes();
            File newFile = new File("/home/vadim/manluck_data/user_img/"
                    + user.getUsername().toLowerCase().replaceAll("\\s+", "") + ".png");
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile));
            outputStream.write(bytes);
            user.setUser_img(newFile.getAbsolutePath());
            userService.editUser(user);
            model.addAttribute("user", user);
            outputStream.close();
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

    @ResponseBody
    @RequestMapping(value = "/update/deactivate")
    public String deactivateUser(@ModelAttribute User user) {
        if (user == null) {
            return "Error on deactivate";
        }
        user.setActive(false);
        userService.editUser(user);
        return "Account deactivate";
    }

}
