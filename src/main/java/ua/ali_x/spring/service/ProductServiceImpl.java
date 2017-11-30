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

    public Product getProduct(Integer p_id) {
        return productDAO.getProduct(p_id);
    }

    @Override
    public void create(Product product) {
        productDAO.create(product);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public List<Product> getTopProducts() {
        return productDAO.getTopProducts();
    }

    @Override
    public List<Product> getByName(String name) {
        return productDAO.getByName(name);
    }
}
