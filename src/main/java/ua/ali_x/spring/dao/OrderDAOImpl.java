package ua.ali_x.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.ali_x.spring.model.Order;
import ua.ali_x.spring.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> getAll(Integer userId) {
        String sql = "SELECT ID, DATE FROM ORDERS WHERE U_ID = ?";
        List<Order> list = jdbcTemplate.query(
                sql, new Object[]{userId},
                (ResultSet rs, int rowNum) -> {
                    Order c = new Order();
                    c.setId(rs.getInt(1));
                    c.setDate(rs.getDate(2));
                    return c;
                });
        for (Order o : list) {
            BigDecimal total = new BigDecimal(0);
            Map<Product, Integer> c = new HashMap<>();
            sql = "SELECT P.NAME, OP.NUM, (P.PRICE * OP.NUM) AS TOTAL " +
                    "FROM ORDERTOPRODUCT OP " +
                    "JOIN PRODUCT P ON OP.P_ID = P.ID " +
                    "JOIN ORDERS O ON OP.O_ID = O.ID " +
                    "WHERE O.U_ID = ? AND OP.O_ID = ?;";
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, new Object[]{userId, o.getId()});
            for (Map m : results) {
                total = total.add((BigDecimal) m.get("TOTAL"));
                Product product = new Product();
                product.setName(m.get("NAME").toString());
                product.setPrice((BigDecimal) m.get("TOTAL"));
                c.put(product, (Integer) m.get("NUM"));
            }
            o.setProducts(c);
            o.setPrice(total);
        }
        return list;
    }

    @Override
    public void addOrder(Integer userId, int p_id) {
        //TODO replace integers by list<integer>
        String sql = "SELECT ID FROM ORDERS WHERE DATE = CURRENT_DATE()";
        List<Integer> o_id = jdbcTemplate.query(
                sql,
                (ResultSet rs, int rowNum) -> {
                    Integer c;
                    c = rs.getInt(1);
                    return c;
                });
        if (o_id.size() == 0) {
            sql = "INSERT INTO ORDERS(ID, U_ID, DATE) VALUES (NULL, ?, CURRENT_DATE());";
            jdbcTemplate.update(sql, new Object[]{userId});
            sql = "SELECT ID FROM ORDERS WHERE DATE = CURRENT_DATE()";
            o_id = jdbcTemplate.query(
                    sql,
                    (ResultSet rs, int rowNum) -> {
                        Integer c;
                        c = rs.getInt(1);
                        return c;
                    });
            sql = "INSERT INTO ORDERTOPRODUCT(ID, O_ID, P_ID, NUM) VALUES (NULL, ?, ?, ?);";
            jdbcTemplate.update(sql, new Object[]{o_id.get(0), p_id, 1});
        } else {
            sql = "SELECT ID FROM ORDERTOPRODUCT WHERE O_ID = ? AND P_ID = ?";
            List<Integer> op_id = jdbcTemplate.query(
                    sql, new Object[]{o_id.get(0), p_id},
                    (ResultSet rs, int rowNum) -> {
                        Integer c;
                        c = rs.getInt(1);
                        return c;
                    });
            if (op_id.size() == 0) {
                sql = "INSERT INTO ORDERTOPRODUCT(ID, O_ID, P_ID, NUM) VALUES (NULL, ?, ?, ?);";
                jdbcTemplate.update(sql, new Object[]{o_id.get(0), p_id, 1});
            } else {
                sql = "SELECT NUM FROM ORDERTOPRODUCT WHERE O_ID = ? AND P_ID = ?";
                List<Integer> op_num = jdbcTemplate.query(
                        sql, new Object[]{o_id.get(0), p_id},
                        (ResultSet rs, int rowNum) -> {
                            Integer c;
                            c = rs.getInt(1);
                            return c;
                        });
                int tempNum = op_num.get(0);
                ++tempNum;
                sql = "UPDATE ORDERTOPRODUCT SET NUM = ? WHERE ID = ?;";
                jdbcTemplate.update(sql, new Object[]{tempNum, op_id.get(0)});
            }
        }

    }
}
