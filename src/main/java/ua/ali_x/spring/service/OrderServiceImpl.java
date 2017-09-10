package ua.ali_x.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.ali_x.spring.dao.OrderDAO;
import ua.ali_x.spring.model.Order;

import java.util.Date;
import java.util.List;
@Repository
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<Order> getAll(Integer userId) {
        return orderDAO.getAll(userId);
    }

    @Override
    public void addOrder(Integer userId, int p_id) {
        orderDAO.addOrder(userId, p_id);
    }
}
