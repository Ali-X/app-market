package ua.ali_x.spring.service;

import ua.ali_x.spring.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByRoleName(String roleName);
    List<Role> getAll();

}
