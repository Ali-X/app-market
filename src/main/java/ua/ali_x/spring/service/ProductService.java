package ua.ali_x.spring.service;


import ua.ali_x.spring.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> getAll(Integer id);

    List<Product> getAll();

    Product getProduct(Integer c_id, Integer p_id);

    void create(String name, String description, Integer c_id, BigDecimal price);

    void delete(Integer id);

    void update(Integer id, String new_name, String new_descr, BigDecimal price, String c_name);

}
