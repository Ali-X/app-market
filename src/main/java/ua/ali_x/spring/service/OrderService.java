package ua.ali_x.spring.service;

import ua.ali_x.spring.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> getAll(Integer userId);
    void addOrder(Integer userId, int p_id);
}
