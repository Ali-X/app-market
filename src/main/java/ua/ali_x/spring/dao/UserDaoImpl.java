package ua.ali_x.spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.controller.admin.UserController;
import ua.ali_x.spring.model.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDAO implements UserDao {

    public void create(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    public void update(User user) {
        this.sessionFactory.getCurrentSession().update(user);
    }

    public void delete(User user) {
        this.sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public List<User> getAll() {
        String query = "FROM User ";
        List<User> list = this.sessionFactory.getCurrentSession()
                .createQuery(query).list();
        return list;
    }

    public User getByUserName(String username) {
        String query = "from User where username =:name";
        return (User) this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("name", username)
                .uniqueResult();
    }
}
