package ua.ali_x.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.dao.UserDao;
import ua.ali_x.spring.model.Roles;
import ua.ali_x.spring.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Qualifier
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public User findUser(String name, String password) {
        return userDao.findByNamePassword(name, password);
    }

    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public List<User> getRoles() {
        return userDao.getRoles();
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public void setRole(Integer id, String role) {
        userDao.setRole(id, role);
    }

    @Override
    public void delRole(Integer id, String role) {
        userDao.delRole(id, role);
    }

    @Override
    public Set<Roles> getAllRoles() {
        Class cls = Roles.class;
        Set<Roles> s = new HashSet<>();
        for (Object obj : cls.getEnumConstants()) {
            s.add((Roles) obj);
        }
        return s;
    }

    @Override
    public void update(Integer id, String username, String password, String email) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userDao.update(user);
    }

    @Override
    public void saveImage(User user, MultipartFile file) {
        userDao.saveImage(user, file);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
