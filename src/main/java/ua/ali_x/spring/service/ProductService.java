package ua.ali_x.spring.service;


import ua.ali_x.spring.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> getAll(Integer id);

    List<Product> getAll();

    Product getProduct(Integer p_id);

    void create(Product product);

    void delete(Product product);

    void update(Product product);

    List<Product> getTopProducts();

    List<Product> getByName(String name);
}
