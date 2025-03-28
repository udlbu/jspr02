package com.example.jspr.webflux.example._3_controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/annotations")
public class AController {

    @GetMapping("/mono")
    public Mono<String> getMono() {
        return Mono
                .just("Hello from Mono")           // Zwraca pojedynczy element Mono
                .log();                                 // Logowanie procesu
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFlux() {
        return Flux
                .just("Hello", "from", "Flux")   // Zwraca sekwencję elementów w Flux
                .delayElements(Duration.ofSeconds(1))  // Dodanie opóźnienia 1 sekundy między elementami
                .log();                                // Logowanie procesu
    }
}