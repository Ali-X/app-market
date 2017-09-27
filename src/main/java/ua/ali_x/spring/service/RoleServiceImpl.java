package ua.ali_x.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ali_x.spring.dao.RoleDAO;
import ua.ali_x.spring.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleDAO.getRole(roleName);
    }

    @Override
    public void delRole(Role role) {
        roleDAO.delRole(role);
    }

    @Override
    public List<Role> getAll() {
        return roleDAO.getAll();
    }

    @Override
    public void setRole(Integer id, String role) {
        roleDAO.setRole(id, role);
    }
}
