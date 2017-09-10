package ua.ali_x.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.ali_x.spring.model.Category;
import ua.ali_x.spring.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(String name, String description, Integer c_id, BigDecimal price) {
        String sql = "INSERT INTO PRODUCT (NAME, DESCRIPTION, C_ID, PRICE) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{name, description, c_id, price});
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM PRODUCT WHERE ID = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    public void update(Integer id, String new_name, String new_descr, BigDecimal new_price, String c_name) {
        String sql = "SELECT ID FROM CATEGORY WHERE NAME = ?";
        int c_id = jdbcTemplate.queryForObject(sql, new Object[]{c_name}, (rs, rm) -> {
            Integer temp;
            temp = rs.getInt("ID");
            return temp;
        });
        sql = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, C_ID = ?, PRICE = ? WHERE ID = ?";
        jdbcTemplate.update(sql, new Object[]{new_name, new_descr, c_id, new_price, id});
    }


    public List<Product> getAll(Integer c_id) {
        String sql = "SELECT * FROM PRODUCT WHERE C_ID = ?";
        List<Product> list = jdbcTemplate.query(
                sql,
                new Object[]{c_id},
                (ResultSet rs, int rowNum) ->
                {
                    Product result = new Product();
                    result.setId(rs.getInt("ID"));
                    result.setName(rs.getString("NAME"));
                    result.setDescription(rs.getString("description"));
                    result.setPrice(rs.getBigDecimal("price"));
                    result.setCategory(null);
                    return result;
                }
        );
        return list;
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT P.id as pid, P.name as pname, P.description, P.price, C.id as cid, C.name as cname FROM PRODUCT P JOIN Category C on P.c_id = C.id";
        List<Product> list = jdbcTemplate.query(
                sql,
                (ResultSet rs, int rowNum) ->
                {
                    Category category = new Category();
                    category.setId(rs.getInt("cid"));
                    category.setName(rs.getString("CNAME"));
                    Product result = new Product();
                    result.setId(rs.getInt("ID"));
                    result.setName(rs.getString("NAME"));
                    result.setDescription(rs.getString("description"));
                    result.setPrice(rs.getBigDecimal("price"));
                    result.setCategory(category);
                    return result;
                }
        );
        return list;
    }

    public Product getProduct(Integer c_id, Integer p_id) {
        Product product = null;
        String sql = "SELECT * FROM PRODUCT WHERE C_ID = ? AND ID = ?";
        product = jdbcTemplate.queryForObject(sql, new Object[]{c_id, p_id}, (rs, rm) -> {
            Product result = new Product();
            result.setId(rs.getInt("ID"));
            result.setName(rs.getString("NAME"));
            result.setDescription(rs.getString("description"));
            result.setPrice(rs.getBigDecimal("price"));
            result.setCategory(null);
            return result;
        });
        return product;
    }
}
