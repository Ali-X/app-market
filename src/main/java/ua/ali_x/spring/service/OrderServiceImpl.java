package ua.ali_x.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.ali_x.spring.dao.OrderDAO;
import ua.ali_x.spring.model.Order;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.model.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<Order> getAll(User user) {
        return orderDAO.getAll(user);
    }

    @Override
    public void addOrder(User user, Product product) {
        Order order = orderDAO.getOrderToday(user);
        List<Product> products;
        if (order == null) {
            order = new Order();
            Date date = new Date(System.currentTimeMillis());
            products = new ArrayList<>();
            products.add(product);
            order.setDate(date);
            order.setProducts(products);
            order.setUser(user);
            orderDAO.create(order);
        } else {
            order.addProduct(product);
            orderDAO.update(order);
        }
    }
}
