package ua.ali_x.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ali_x.spring.model.Role;

import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl extends AbstractDAO implements RoleDAO {
    @Override
    public Role getRole(String roleName) {
        String query = "from Role where name =:roleName";
        return (Role) sessionFactory.getCurrentSession().createQuery(query).setParameter("roleName", roleName).uniqueResult();
    }

    @Override
    public void delRole(Role role) {
        this.sessionFactory.getCurrentSession().delete(role);
    }

    @Override
    public List<Role> getAll() {
        String query = "FROM Role ";
        List<Role> list = this.sessionFactory.getCurrentSession()
                .createQuery(query).list();
        return list;
    }

    @Override
    public void setRole(Integer id, String role) {

    }
}
