package ua.ali_x.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.ali_x.spring.dao.UserDao;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.validation.UserPropertiesValidator;

import java.util.List;

@Service
@Qualifier
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserPropertiesValidator userPropertiesValidator;

    public void create(User user) {
        if (userPropertiesValidator.validate(user)) {
            userDao.create(user);
        }
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getByUserName(String username) {
        return userDao.getByUserName(username);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
