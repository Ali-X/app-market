package ua.ali_x.spring.service;

import ua.ali_x.spring.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByRoleName(String roleName);
    void delRole(Role role);
    List<Role> getAll();
    void setRole(Integer id, String role);

}
