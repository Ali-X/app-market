package ua.ali_x.spring.dao;


import ua.ali_x.spring.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {

    List<Product> getAll(Integer c_id);

    List<Product> getAll();

    Product getProduct(Integer c_id, Integer p_id);

    void create(String name, String description, Integer c_id, BigDecimal price);

    void delete(Integer id);

    void update(Integer id, String new_name, String new_descr, BigDecimal new_price, String c_name);
}
