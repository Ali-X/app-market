package ua.ali_x.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ali_x.spring.model.Order;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.model.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.*;

@Repository
@Transactional
public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

    @Override
    public List<Order> getAll(User user) {
        String query = "FROM Order O " +
                "WHERE O.user.id =:u_id";
        List<Order> list = this.sessionFactory.getCurrentSession()
                .createQuery(query).setParameter("u_id", user.getId()).list();
        return list;
    }

    public Order getOrderToday(User user) {
        String query = "FROM Order O " +
                "WHERE O.user.id =:u_id and O.date = CURRENT_DATE ";
        Order order = (Order)this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("u_id", user.getId())
                .uniqueResult();
        return order;
    }

    @Override
    public void create(Order order) {
        this.sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public void update(Order order) {
        this.sessionFactory.getCurrentSession().update(order);
    }
}
