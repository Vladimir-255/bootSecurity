package com.example.bootsecurity.controllers;

import com.example.bootsecurity.model.Role;
import com.example.bootsecurity.model.User;
import com.example.bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String allUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allusers";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getByID(id));
        return "updateUser";
    }

    @PutMapping(value = "/admin/{id}/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/admin/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String findUser(Model model, Principal principal) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/admin/adduser")
    public String addUser(@ModelAttribute("user") User user) {
        return "adduser";
    }

    @PostMapping(value = "/admin")
    public String createUser(@ModelAttribute("user") User user) {
        user.getRoles().add(userService.findRoleByRole("USER"));
        userService.addUser(user);
        return "redirect:/admin";

    }

    @PostMapping("/admin/addroleadmin")
    public String addRoleAdmin(@ModelAttribute("user") User user) {
        user.getRoles().add(userService.findRoleById(1));
        userService.updateUser(user);
        return "redirect:/admin";
    }
//
//    @PostMapping("admin/addroleuser")
//    public String addRoleUser(@ModelAttribute("user") User user) {
//        user.setRoles((Set<Role>) userService.findRoleById(2));
//        userService.updateUser(user);
//        return "redirect:/admin";
//    }
}


