package ua.ali_x.spring.service;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.model.Roles;
import ua.ali_x.spring.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAll();

    User findUser(String name, String password);

    User findByToken(String token);

    void create(User user);

    List<User> getRoles();

    void delete(Integer id);

    void setRole(Integer id, String role);

    void delRole(Integer id, String role);

    Set<Roles> getAllRoles();

    void update(Integer id, String username, String password, String email);

    void saveImage(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file);

}
