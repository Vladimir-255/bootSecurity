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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        //userDao.addUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
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
        return userRepository.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        //userDao.updateUser(user, id);
        userRepository.save(user);

    }


    @Override
    public User findByUserName(String username) {
        // return userDao.findByUserName(username);
        return userRepository.findByUsername(username);

    }

}