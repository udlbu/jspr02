package com.example.jspr.event;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    private final String msg;

    public CustomEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}