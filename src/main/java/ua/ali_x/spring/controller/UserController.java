package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.Roles;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login", "user", new User());
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("user") User user, HttpServletResponse response) {
        ModelAndView mv;
        User userInput = userService.findUser(user.getUsername(), user.getPassword());
        if (userInput != null) {
            if (userInput.getRoles().contains(Roles.ADMIN)) {
                mv = new ModelAndView("admin", "user", userInput);
            } else {
                mv = new ModelAndView("profile", "user", userInput);
            }
            response.addCookie(new Cookie("token", userInput.getToken()));
        } else {
            mv = new ModelAndView("login");
            System.out.println("Username or password is incorrect");
        }
        return mv;
    }

    @RequestMapping(value = "/admin/user/upd", method = RequestMethod.GET)
    public ModelAndView updateUser(@RequestParam("u_id") Integer id) {
        ModelAndView mv = new ModelAndView("editUser", "user", new User());
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping(value = "/admin/user/upd", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView updateUser(@ModelAttribute("user") User user, @RequestParam("u_id") Integer id,
                            @RequestParam("file") MultipartFile file) {
        userService.saveImage(user, file);
        userService.update(id, user.getUsername(), user.getPassword(), user.getEmail());
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/user/del", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("u_id") Integer id) {
        userService.delete(id);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/user/role/add", method = RequestMethod.POST)
    public ModelAndView addRole(@ModelAttribute("user") User user, @RequestParam("u_role") String role) {
        userService.setRole(user.getId(), role);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/user/role/del", method = RequestMethod.GET)
    public ModelAndView delRole(@RequestParam("u_id") Integer id, @RequestParam("ur_name") String rolename) {
        userService.delRole(id, rolename);
        return new ModelAndView("success");
    }

}
