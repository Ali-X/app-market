package ua.ali_x.spring.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.ali_x.spring.dao.ProductDAO;
import ua.ali_x.spring.model.Product;

import java.math.BigDecimal;
import java.util.List;
@Service
@Qualifier
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAll(Integer c_id) {
        return productDAO.getAll(c_id);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public Product getProduct(Integer c_id, Integer p_id) {
        return productDAO.getProduct(c_id, p_id);
    }

    @Override
    public void create(String name, String description, Integer c_id, BigDecimal price) {
        productDAO.create(name, description, c_id, price);
    }

    @Override
    public void delete(Integer id) {
        productDAO.delete(id);
    }

    @Override
    public void update(Integer id, String new_name, String new_descr, BigDecimal price, String c_name) {
        productDAO.update(id, new_name, new_descr, price, c_name);
    }
}
