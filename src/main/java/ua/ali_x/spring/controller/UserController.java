package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.Role;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.RoleService;
import ua.ali_x.spring.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Value("${user.role.default}")
    private String userRoleDefault;

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login", "user", new User());
        return mv;
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration(){
        ModelAndView mv = new ModelAndView("registration", "user", new User());
        return mv;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView registration(@ModelAttribute("user") User user,
                                   @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView();
        String token = user.getUsername() + System.nanoTime();
        user.setRole(roleService.getRoleByRoleName(userRoleDefault));
        user.setToken(token);
        user.setEnabled(Boolean.TRUE);
        userService.create(user);
        userService.saveImage(user, file);
        mv.setViewName("home");
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
        user.setId(id);
        userService.update(user);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/user/del", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("u_id") Integer id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/user/role/add", method = RequestMethod.POST)
    public ModelAndView addRole(@ModelAttribute("user") User user, @RequestParam("r_name") String r_name) {
        user = userService.getByUserName(user.getUsername());
        Role role = roleService.getRoleByRoleName(r_name);
        user.setRole(role);
        userService.update(user);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/user/role/del", method = RequestMethod.GET)
    public ModelAndView delRole(@RequestParam("u_name") String u_name, @RequestParam("r_name") String r_name) {
        User user = userService.getByUserName(u_name);
        Role role = roleService.getRoleByRoleName(r_name);
        user.removeRole(role);
        userService.update(user);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/auth/denied")
    public ModelAndView accessDenied() {
        ModelAndView mv = new ModelAndView("accessDenied");
        return mv;
    }

}
