package com.example.bootsecurity.service;

import com.example.bootsecurity.model.Role;
import com.example.bootsecurity.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user);
    void addRoleByUsername(String username, Role role);
    void deleteRole(String username, String role);
    Set<Role> getAllRoles(String username);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User getByID(long id);
    void updateUser(User user);
    User findByUserName(String username);
Role findRoleByRole(String nameRole);
Role findRoleById(long id);
    }

