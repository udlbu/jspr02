package com.example.jspr.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        System.out.println("Received CustomEvent: " + customEvent.getMsg());
        System.out.println("Processing event on thread: " + Thread.currentThread().getName());
    }
}