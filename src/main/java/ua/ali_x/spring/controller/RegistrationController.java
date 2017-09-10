package ua.ali_x.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.Roles;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("registration", "user", new User());
        return mv;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView uploadFileHandler(@ModelAttribute("user") User user,
                             @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView();
        userService.saveImage(user, file);
        String token = user.getUsername() + System.nanoTime();
        Set<Roles> role = new HashSet<Roles>();
        role.add(Roles.USER);
        user.setToken(token);
        user.setRoles(role);
        userService.create(user);
        mv.setViewName("home");
        return mv;
    }



}
