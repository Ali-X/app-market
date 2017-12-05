package ua.ali_x.spring.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ali_x.spring.model.Role;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.ImageService;
import ua.ali_x.spring.service.RoleService;
import ua.ali_x.spring.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRoles() {
        return roleService.getAll();
    }

/*    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user,
                            @RequestParam("file") MultipartFile file) {
        imageService.saveImage(user.getUsername(), file);
        userService.update(user);
        return user;
    }*/

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }

    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public User addRole(@RequestBody User user, @RequestBody Role role) {
        user = userService.getByUserName(user.getUsername());
        user.setRole(role);
        userService.update(user);
        return user;
    }

    @RequestMapping(value = "/role/remove", method = RequestMethod.DELETE)
    public User delRole(@RequestBody User user, @RequestBody Role role) {
        user.removeRole(role);
        userService.update(user);
        return user;
    }

}
