package com.example.jspr.redis.controller;



import com.example.jspr.redis.model.UserSession;
import com.example.jspr.redis.repository.UserSessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/session")
public class UserSessionController {
    private final UserSessionRepository repository;

    public UserSessionController(UserSessionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/{id}")
    public UserSession createSession(@PathVariable String id, @RequestParam String data) {
        return repository.save(new UserSession(id, data));
    }

    @GetMapping("/{id}")
    public Optional<UserSession> getSession(@PathVariable String id) {
        return repository.findById(id);
    }
}
