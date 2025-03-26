package com.example.jspr.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(String eventMsg) {
        // Poszukaj komunikatu w logach
        System.out.println("Publishing event from thread: " + Thread.currentThread().getName());
        applicationEventPublisher.publishEvent(new CustomEvent(this, eventMsg));
    }
}
