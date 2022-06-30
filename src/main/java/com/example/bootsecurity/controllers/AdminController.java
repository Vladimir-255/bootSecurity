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

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @GetMapping(value = "")
    public String allUser() {
        return "allusers";
    }
}