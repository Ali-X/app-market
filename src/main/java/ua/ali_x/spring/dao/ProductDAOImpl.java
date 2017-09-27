package ua.ali_x.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ali_x.spring.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl extends AbstractDAO implements ProductDAO {

    public void create(Product product) {
        this.sessionFactory.getCurrentSession().save(product);
    }

    public void delete(Product product) {
        this.sessionFactory.getCurrentSession().delete(product);
    }

    public void update(Product product) {
        this.sessionFactory.getCurrentSession().update(product);
    }


    public List<Product> getAll(Integer c_id) {
        String query = "FROM Product as product where product.category.id =:c_id";
        List<Product> list = this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("c_id", c_id).list();
        return list;
    }

    @Override
    public List<Product> getAll() {
        String query = "FROM Product ";
        List<Product> list = this.sessionFactory.getCurrentSession()
                .createQuery(query).list();
        return list;
    }

    public Product getProduct(Integer c_id, Integer p_id) {
        String query = "FROM Product as product where product.category.id =:c_id and product.id =:p_id";
        return (Product) this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("c_id", c_id)
                .setParameter("p_id", p_id)
                .uniqueResult();
    }
}
