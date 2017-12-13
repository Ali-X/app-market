package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.model.Order;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.ImageService;
import ua.ali_x.spring.service.OrderService;
import ua.ali_x.spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/user")
public class UserPageController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] image(@RequestParam("name") String username) throws IOException {
        return imageService.getImage(username);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public User getProfile(HttpServletRequest request) {
        return userService.getByUserName(request.getRemoteUser());
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrders(HttpServletRequest request) {
        User user = userService.getByUserName(request.getRemoteUser());
        return orderService.getAll(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User updateUser(@ModelAttribute("user") User user,
                           @RequestParam("file") MultipartFile file) {
        imageService.saveImage(user.getUsername(), file);
        userService.update(user);
        return user;
    }


}
