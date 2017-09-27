package ua.ali_x.spring.dao;

import ua.ali_x.spring.model.Role;

import java.util.List;

public interface RoleDAO {

    Role getRole(String roleName);
    void delRole(Role role);
    List<Role> getAll();
    void setRole(Integer id, String role);
}
