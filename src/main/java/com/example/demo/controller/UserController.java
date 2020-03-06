package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        userService.createTable();
        this.userService = userService;
    }


    @PostMapping(value = "/user")
    public void create(@RequestBody User user){
        if (userService.createUser(user)==1) {
            System.out.println("Created");
        }else{
            System.out.println("Something went wrong");
        }
    }

    @PutMapping(value = "/user/{id}")
    public void update(@RequestBody User user, @PathVariable int id){
        if (userService.updateUser(user,id)==1) {
            System.out.println("Updating user with id " + id);
        }else{
            System.out.println("Something went wrong");
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable int id){
        if (userService.deleteUser(id)==1) {
            System.out.println("Deleting user with id " + id);
        }else{
            System.out.println("Something went wrong");
        }
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> findById(@PathVariable int id){
        User user=userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> findAll(){
        List<User> users=userService.findAll();
        return ResponseEntity.ok(users);
    }
}
