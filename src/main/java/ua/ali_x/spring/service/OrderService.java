package ua.ali_x.spring.service;

import ua.ali_x.spring.model.Order;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.model.User;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> getAll(User user);
    void addOrder(User user, Product product);
}
