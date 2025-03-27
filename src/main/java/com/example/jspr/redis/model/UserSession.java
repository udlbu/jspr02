package com.example.jspr.redis.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash("UserSession")
public class UserSession {
    @Id
    private String id;


    private String data;

    public UserSession(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }
}

