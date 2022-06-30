package com.example.bootsecurity.controllers;

import com.example.bootsecurity.dto.UserDto;
import com.example.bootsecurity.model.User;
import com.example.bootsecurity.service.MapingUser;
import com.example.bootsecurity.service.RoleService;
import com.example.bootsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/api")
public class RestControllers {

    UserService userService;
    RoleService roleService;
    MapingUser mapingUser;

    public RestControllers(UserService userService, RoleService roleService, MapingUser mapingUser) {
        this.userService = userService;
        this.roleService = roleService;
        this.mapingUser = mapingUser;

    }

    @GetMapping(value = "/user")
    public ResponseEntity<User> curentUser(Principal principal){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id")Long id){
        User user = userService.getByID(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
        User user = mapingUser.mapDtoToUSer(userDto);
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto){
        System.out.println("!!!!!!!!!!!!!!!!print userDTO");
        System.out.println(userDto);
        User user = mapingUser.mapDtoToUSer(userDto);
        System.out.println("!!!!!!!!!!!!!!!!print user");
        System.out.println(user);
        userService.updateUser(user);
        System.out.println("!!!!!!!!!!!!!user update");
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("user deleted");
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> allUser(){
        List<User> userList = userService.getAllUsers();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!! print all users");
        return ResponseEntity.ok().body(userList);
    }

}
