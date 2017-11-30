package ua.ali_x.spring.dao;

import ua.ali_x.spring.model.Role;

import java.util.List;

public interface RoleDAO {

    Role getRole(String roleName);
    List<Role> getAll();
}
