package ua.ali_x.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.ali_x.spring.model.Category;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(String category) {
        String sql = "INSERT INTO CATEGORY (NAME) VALUES(?)";
        jdbcTemplate.update(sql, new Object[]{category});
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM CATEGORY WHERE ID = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    public void update(Integer id, String new_name) {
        String sql = "UPDATE CATEGORY SET NAME = ? WHERE ID = ?";
        jdbcTemplate.update(sql, new Object[]{new_name, id});
    }

    public List<Category> getAll() {
        String sql = "SELECT * FROM CATEGORY";
        List<Category> list = jdbcTemplate.query(
                sql,
                (ResultSet rs, int rowNum) -> {
                        Category c = new Category();
                        c.setId(rs.getInt(1));
                        c.setName(rs.getString(2));
                        return c;
                });
        return list;
    }
}
