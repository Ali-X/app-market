package ua.ali_x.spring.service;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.model.Role;
import ua.ali_x.spring.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAll();

    User findByToken(String token);

    void create(User user);

    void delete(User user);

    void update(User user);

    void saveImage(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file);

    User getByUserName(String username);

}
