package com.example.bootsecurity.service;


import com.example.bootsecurity.model.Role;
import com.example.bootsecurity.model.User;
import com.example.bootsecurity.repositories.RoleRepository;
import com.example.bootsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findRoleByRole(String nameRole) {
        return roleRepository.findRoleByRole(nameRole);
    }

    @Override
    public void addRoleByUsername(String username, Role role) {
        User user = userRepository.findByUserName(username);
        user.getRoles().add(role);
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();

    }

    @Override
    public void deleteRole(String username, String nameRole) {
        User user = userRepository.findByUserName(username);
        Role role = roleRepository.findRoleByRole(nameRole);
        user.getRoles().remove(role);
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findRoleById(id);
    }
}
