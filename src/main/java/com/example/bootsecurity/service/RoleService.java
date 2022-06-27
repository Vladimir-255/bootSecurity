package com.example.bootsecurity.service;

import com.example.bootsecurity.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findRoleByRole(String nameRole);
    void addRoleByUsername(String username, Role role);
    void deleteRole(String username, String role);
    List<Role> getAllRoles();
    Role findRoleById(long id);
}
