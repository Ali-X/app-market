package ua.ali_x.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ali_x.spring.model.Category;
import java.sql.ResultSet;
import java.util.List;

@Repository
@Transactional
public class CategoryDAOImpl extends AbstractDAO implements CategoryDAO {

    public void create(Category category) {
        this.sessionFactory.getCurrentSession().save(category);
    }

    public void delete(Category category) {
        this.sessionFactory.getCurrentSession().delete(category);
    }

    public void update(Category category) {
        this.sessionFactory.getCurrentSession().update(category);
    }

    public List<Category> getAll() {
        String query = "FROM Category ";
        List<Category> list = this.sessionFactory.getCurrentSession()
                .createQuery(query).list();
        return list;
    }
}
