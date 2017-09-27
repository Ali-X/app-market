package ua.ali_x.spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.controller.UserController;
import ua.ali_x.spring.model.Role;
import ua.ali_x.spring.model.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public User findByToken(String token) {
        String query = "FROM User U " +
                "JOIN U.roles as Role " +
                "WHERE U.token =:token ";
        return (User) this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("token", token)
                .uniqueResult();
    }

    public User getByUserName(String username) {
        String query = "from User where username =:name";
        return (User) this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("name", username)
                .uniqueResult();
    }

    public void saveImage(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file) {
        final Logger logger = LoggerFactory
                .getLogger(UserController.class);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                Path currentRelativePath = Paths.get("images");
                String uploadPath = currentRelativePath.toAbsolutePath().toString();
                File dir = new File(uploadPath);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + user.getUsername() + ".png");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                System.out.println("You successfully uploaded file=" + serverFile.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("You failed to upload " + user.getUsername() + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + user.getUsername()
                    + " because the file was empty.");
        }
    }
}
