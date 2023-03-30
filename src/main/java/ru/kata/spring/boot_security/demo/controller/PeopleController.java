package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserDetailService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.utill.UserValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller

public class PeopleController {

    private final UserService userService;

    private UserDetailService userDetailService;

    private RoleRepository roleRepository;

    private UserValidator userValidator;

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    public PeopleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/")
    public String getAllPeople(Model model, Principal principal) {
        User userPrincipal = userDetailService.findByUsername(principal.getName());
        model.addAttribute("personPrincipal", userPrincipal);
        List<User> list = userService.getAllPeople();
        User user = new User();
        model.addAttribute("person", user);
        model.addAttribute("allPeople", list);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "allPeople";
    }

    @GetMapping("/admin/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", userService.getById(id));
        return "showUser";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        User user = userDetailService.findByUsername(principal.getName());

        model.addAttribute("person", user);
        System.out.println(user.getRoles());
        for(Role role:user.getRoles()){
            if(role.getName().equals("ROLE_ADMIN")) {
                return "showUserRoleAdmin";
            }
        }
        return "showUser";
    }

}
