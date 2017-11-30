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

    @Override
    public List<Product> getTopProducts() {
        String query = "FROM Product as product ORDER BY product.rating desc ";
        List<Product> list = this.sessionFactory.getCurrentSession()
                .createQuery(query).list();
        return list;
    }

    @Override
    public List<Product> getByName(String name) {
        String query = "FROM Product as product where lower(product.name) like lower(:name)";
        List<Product> list = this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("name", "%"+name+"%").list();
        return list;
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

    public Product getProduct(Integer p_id) {
        String query = "FROM Product as product where product.id =:p_id";
        return (Product) this.sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("p_id", p_id)
                .uniqueResult();
    }
}
