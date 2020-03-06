package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    public void findById(@PathVariable int id){
        if (userService.getUserById(id)!=null) {
            System.out.println("getting by id " + id);
            System.out.println(userService.getUserById(id));
        }else{
            System.out.println("Something went wrong");
        }
    }

    @GetMapping(value = "/user")
    public void findAll(){
        System.out.println("All users");
        userService.findAll().forEach(System.out::println);
    }
}
