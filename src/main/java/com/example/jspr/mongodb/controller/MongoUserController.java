package com.example.jspr.mongodb.controller;



import com.example.jspr.mongodb.model.User;
import com.example.jspr.mongodb.repository.MongoUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo-users")
public class MongoUserController {
    private final MongoUserRepository repository;

    public MongoUserController(MongoUserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
