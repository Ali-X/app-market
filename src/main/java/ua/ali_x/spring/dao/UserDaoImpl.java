package ua.ali_x.spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.controller.RegistrationController;
import ua.ali_x.spring.model.Roles;
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
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(User user) {
        Integer userid = null;
        Integer roleid = null;
        String sql = "INSERT INTO USERS (USERNAME, TOKEN, PASSWORD, EMAIL) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getToken(),
                                              user.getPassword(), user.getEmail()});

        sql = "SELECT U.ID FROM USERS U WHERE U.USERNAME = ?";
        userid = jdbcTemplate.queryForObject(sql, new Object[]{user.getUsername()}, (rs, rm) -> {
            Integer temp;
            temp = rs.getInt("ID");
            return temp;
        });
        for (Roles r : user.getRoles()) {
            sql = "SELECT R.ID FROM ROLES R WHERE R.NAME = ?";
            roleid = jdbcTemplate.queryForObject(sql, new Object[]{r.name()}, (rs, rm) -> {
                Integer temp;
                temp = rs.getInt("ID");
                return temp;
            });
            sql = "INSERT INTO USERTOROLE (USERID, ROLEID) VALUES (?, ?)";
            jdbcTemplate.update(sql, new Object[]{userid, roleid});
        }
    }

    public void update(User user) {
        String sql = "UPDATE USERS " +
                "SET USERNAME = ?, PASSWORD = ?, EMAIL = ? " +
                "WHERE ID = ?;";
        jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(),
                                              user.getEmail(), user.getId()});
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.EMAIL " +
                "FROM USERS U;";
        users = jdbcTemplate.query(sql, (rs, rm) -> {
            User result = new User();
            result.setId(rs.getInt("ID"));
            result.setUsername(rs.getString("USERNAME"));
            result.setEmail(rs.getString("EMAIL"));
            result.setPassword(rs.getString("PASSWORD"));
            return result;
        });
        return users;
    }

    public List<User> getRoles() {
        List<User> users;
        String sql = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.EMAIL, R.NAME " +
                "FROM USERS U " +
                "JOIN USERTOROLE UR ON UR.USERID=U.ID " +
                "JOIN ROLES R ON R.ID = UR.ROLEID;";
        users = jdbcTemplate.query(sql, (rs, rm) -> {
            User result = new User();
            result.setId(rs.getInt("ID"));
            result.setUsername(rs.getString("USERNAME"));
            result.setEmail(rs.getString("EMAIL"));
            result.setPassword(rs.getString("PASSWORD"));
            Set<Roles> roles = new HashSet<>();
            roles.add(Roles.valueOf(rs.getString("NAME")));
            result.setRoles(roles);
            return result;
        });
        return users;
    }

    @Override
    public void setRole(Integer userID, String role) {
        String sql = "SELECT R.ID FROM ROLES R WHERE R.NAME = ?;";
        Integer roleID = jdbcTemplate.queryForObject(sql, new Object[]{role}, (rs, rm) -> {
            Integer temp;
            temp = rs.getInt("ID");
            return temp;
        });
        sql = "INSERT INTO USERTOROLE (USERID, ROLEID) VALUES (?, ?)";
        jdbcTemplate.update(sql, new Object[]{userID, roleID});
    }

    @Override
    public void delRole(Integer id, String role) {
        String sql = "SELECT R.ID FROM ROLES R WHERE R.NAME = ?;";
        Integer roleID = jdbcTemplate.queryForObject(sql, new Object[]{role}, (rs, rm) -> {
            Integer temp;
            temp = rs.getInt("ID");
            return temp;
        });
        sql = "DELETE FROM USERTOROLE WHERE USERID = ? AND ROLEID = ?;";
        jdbcTemplate.update(sql, new Object[]{id, roleID});
    }

    public User findByToken(String token) {
        Set<Roles> roles = new HashSet<Roles>();
        String sql = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME " +
                "FROM USERS U " +
                "JOIN USERTOROLE UR ON UR.USERID=U.ID " +
                "JOIN ROLES R ON R.ID = UR.ROLEID " +
                "WHERE U.TOKEN = ?;";
        List<User> list = jdbcTemplate.query(sql, new Object[]{token}, (rs, rm) -> {
            User result = new User();
            result.setId(rs.getInt("ID"));
            result.setUsername(rs.getString("USERNAME"));
            result.setEmail(rs.getString("EMAIL"));
            result.setToken(rs.getString("TOKEN"));
            result.setPassword(rs.getString("PASSWORD"));
            roles.add(Roles.valueOf(rs.getString("NAME")));
            result.setRoles(roles);
            return result;
        });
        if (list.size() == 0) return null;
        return list.get(list.size() - 1);
    }

    public User findByNamePassword(String name, String password) {
        Set<Roles> roles = new HashSet<Roles>();
        String sql = "SELECT  U.ID, U.USERNAME, U.PASSWORD, U.TOKEN, U.EMAIL, R.NAME " +
                "FROM USERS U " +
                "JOIN USERTOROLE UR ON UR.USERID=U.ID " +
                "JOIN ROLES R ON R.ID = UR.ROLEID " +
                "WHERE U.USERNAME = ? " +
                "AND U.PASSWORD = ?";
        List<User> list = jdbcTemplate.query(
                sql,
                new Object[]{name, password},
                (ResultSet rs, int rowNum) ->
                {
                    User result = new User();
                    result.setId(rs.getInt("ID"));
                    result.setUsername(rs.getString("USERNAME"));
                    result.setEmail(rs.getString("EMAIL"));
                    result.setToken(rs.getString("TOKEN"));
                    result.setPassword(rs.getString("PASSWORD"));
                    roles.add(Roles.valueOf(rs.getString("NAME")));
                    result.setRoles(roles);
                    return result;
                }
        );
        if (list.size() == 0) return null;
        return list.get(list.size() - 1);
    }


    public User getByUserName(String username) {
        String sql = "SELECT ID, USERNAME, EMAIL, TOKEN, PASSWORD FROM USERS WHERE USERNAME = ?;";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rm) -> {
            User result = new User();
            result.setId(rs.getInt("ID"));
            result.setUsername(rs.getString("USERNAME"));
            result.setEmail(rs.getString("EMAIL"));
            result.setToken(rs.getString("TOKEN"));
            result.setPassword(rs.getString("PASSWORD"));
            return result;
        });
        return user;
    }

    public void saveImage(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file) {
        final Logger logger = LoggerFactory
                .getLogger(RegistrationController.class);

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
