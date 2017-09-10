package ua.ali_x.spring.dao;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.model.User;

import java.util.List;

public interface UserDao {

    User findByToken(String token);

    User findByNamePassword(String name, String password);

    List<User> getRoles();

    void setRole(Integer id, String role);

    void delRole(Integer id, String role);

    List<User> getAll();

    void create(User user);

    void update(User user);

    void delete(Integer id);

    void saveImage(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file);

}
