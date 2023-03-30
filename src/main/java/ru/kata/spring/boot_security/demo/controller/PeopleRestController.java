package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController
public class PeopleRestController {
    private final UserService userService;


    public PeopleRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/get")
    public List<User> getAllPeople() {
        return userService.getAllPeople();
    }

    @GetMapping("/admin/get/{id}")
    public User showUser(@PathVariable int id) {
       return userService.getById(id);

    }

    @PostMapping("/admin/get")
    public User addUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping("/admin/get")
    public User updateUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }
    @DeleteMapping("/admin/get/{id}")
    public User delete(@PathVariable int id) {
        userService.deleteUser(id);
        return userService.getById(id);
    }
}
