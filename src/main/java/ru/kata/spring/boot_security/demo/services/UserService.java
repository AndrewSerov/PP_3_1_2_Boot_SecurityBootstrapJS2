package ru.kata.spring.boot_security.demo.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service

public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userDao) {
        this.userRepository = userDao;
    }

    @Transactional
    public List<User> getAllPeople() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Transactional
    public void updateUser(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User findByUsername (String username) {
        return userRepository.findByUsername(username);
    }
}
