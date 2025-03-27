package com.example.jspr.redis.repository;


import com.example.jspr.redis.model.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, String> {
}
