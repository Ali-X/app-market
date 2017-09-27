package ua.ali_x.spring.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.OrderService;
import ua.ali_x.spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Value("${user.role.default}")
    private String userRoleDefault;
    @Value("${user.role.admin}")
    private String userRoleAdmin;

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

    @RequestMapping(value = "/personalPage", method = RequestMethod.GET)
    public void checkUser(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        if (request.isUserInRole(userRoleAdmin)) {
            response.setHeader("Location", "/admin");
            return;
        }
        if (request.isUserInRole(userRoleDefault)) {
            response.setHeader("Location", "/profile");
            return;
        }
        response.setHeader("Location", "/login");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView getAdmin(HttpServletRequest request) {
        ModelAndView mv;
        mv = new ModelAndView("admin", "user", userService.getByUserName(request.getRemoteUser()));
        return mv;
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(HttpServletRequest request) {
        ModelAndView mv;
        mv = new ModelAndView("profile", "user", userService.getByUserName(request.getRemoteUser()));
        return mv;
    }

    @RequestMapping(value = "/profile/orders", method = RequestMethod.GET)
    public ModelAndView getOrders(HttpServletRequest request) {
        ModelAndView mv;
        User user = userService.getByUserName(request.getRemoteUser());
        mv = new ModelAndView("orders", "orders", orderService.getAll(user));
        mv.addObject("user", user);
        return mv;
    }

}
