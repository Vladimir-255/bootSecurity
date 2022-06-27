package com.example.bootsecurity.controllers;

import com.example.bootsecurity.model.Role;
import com.example.bootsecurity.model.User;
import com.example.bootsecurity.service.RoleService;
import com.example.bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping(value = "")
    public String allUser(Model model, Principal principal) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRole", roleService.getAllRoles());
        return "allusers";
    }

    @GetMapping(value = "/{id}")
    public String findUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getByID(id));
        return "user";
    }


    @GetMapping(value = "/adduser")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRole", roleService.getAllRoles());
        return "adduser";
    }

    @PutMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") Long id,
                             @RequestParam("role") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleService.findRoleByRole(role));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleService.findRoleByRole(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }


//
//    @GetMapping(value = "")
//    public String allUser(Model model) {
//        model.addAttribute("users",userService.getAllUsers());
//        return "allusers";
//    }
//
//    @GetMapping(value = "/{id}/edit")
//    public String editUser(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getByID(id));
//        return "updateUser";
//    }
//
//    @PutMapping(value = "/{id}/update")
//    public String updateUser(@ModelAttribute("user") User user) {
//        userService.updateUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping(value = "/{id}/delete")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping(value = "/adduser")
//    public String addUser(@ModelAttribute("user") User user) {
//        return "adduser";
//    }
//
//    @PostMapping(value = "/save")
//    public String createUser(@ModelAttribute("user") User user) {
//      //  user.getRoles().add(roleService.findRoleById(2));
//        userService.addUser(user);
//        return "redirect:/admin";
//    }
}
