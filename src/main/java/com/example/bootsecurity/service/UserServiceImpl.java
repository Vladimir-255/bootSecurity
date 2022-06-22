package com.example.bootsecurity.service;

import com.example.bootsecurity.model.Role;
import com.example.bootsecurity.model.User;
import com.example.bootsecurity.repositories.RoleRepository;
import com.example.bootsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void addUser(User user) {
        //userDao.addUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void addRoleByUsername(String username, Role role) {
        User user = userRepository.findByUserName(username);
        user.getRoles().add(role);
    }

    @Override
    public void deleteRole(String username, String nameRole) {
        User user = userRepository.findByUserName(username);
        Role role = roleRepository.findRoleByRole(nameRole);
        user.getRoles().remove(role);
    }

    @Override
    public Set<Role> getAllRoles(String username) {
        User user = userRepository.findByUserName(username);
        Set<Role> roleList = user.getRoles();
        return roleList;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        //userDao.deleteUser(id);
        userRepository.deleteById(id);
    }

    @Override
    public User getByID(long id) {
        //return userDao.getByID(id);
        return userRepository.getById(id);
    }

    @Override
    public void updateUser(User user) {
        //userDao.updateUser(user, id);
        userRepository.save(user);

    }

    @Override
    public Role findRoleByRole(String nameRole) {
        return roleRepository.findRoleByRole(nameRole);
    }

    @Override
    public User findByUserName(String username) {
        // return userDao.findByUserName(username);
        return userRepository.findByUserName(username);

    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findRoleById(id);
    }
}