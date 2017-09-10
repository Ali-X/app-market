package ua.ali_x.spring.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import ua.ali_x.spring.model.Roles;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.OrderService;
import ua.ali_x.spring.service.UserService;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] image(@RequestParam("name") String username) throws IOException {
        Path currentRelativePath = Paths.get("images");
        String uploadPath = currentRelativePath.toAbsolutePath().toString();
        String fileName = username + ".png";
        String filePath = uploadPath + File.separator + fileName;
        FileInputStream fin = new FileInputStream(filePath);
        return IOUtils.toByteArray(fin);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView checkUser(@CookieValue(value = "token", defaultValue = "token") String token){
        ModelAndView mv;
        User userInput = userService.findByToken(token);
        if (userInput != null) {
            if (userInput.getRoles().contains(Roles.ADMIN)) {
                mv = new ModelAndView("admin", "user", userInput);
            } else {
                mv = new ModelAndView("profile", "user", userInput);
            }
        } else {
            mv = new ModelAndView("login", "user", new User());
            System.out.println("No user token in cookies");
        }
        return mv;
    }

    @RequestMapping(value = "/profile/orders", method = RequestMethod.GET)
    public ModelAndView getOrders(@CookieValue(value = "token", defaultValue = "token") String token){
        ModelAndView mv;
        User userInput = userService.findByToken(token);
        if (userInput != null) {
            mv = new ModelAndView("orders", "orders", orderService.getAll(userInput.getId()));
            mv.addObject("user", userInput);
        } else mv = new ModelAndView("home");
        return mv;
    }

}
