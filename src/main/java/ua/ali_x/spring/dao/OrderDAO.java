package ua.ali_x.spring.dao;

import ua.ali_x.spring.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderDAO {
    List<Order> getAll(Integer userId);
    void addOrder(Integer userId, int p_id);
}
