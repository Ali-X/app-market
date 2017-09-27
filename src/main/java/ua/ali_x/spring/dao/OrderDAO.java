package ua.ali_x.spring.dao;

import ua.ali_x.spring.model.Order;
import ua.ali_x.spring.model.User;

import java.util.List;

public interface OrderDAO {
    List<Order> getAll(User user);

    Order getOrderToday(User user);

    void create(Order order);

    void update(Order order);
}
