package com.example.jspr.webflux.example._1_reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class _1_MonoAndFlux {
    // Mono: Strumień, który może emitować 0 lub 1 element
    public Mono<String> getMono() {
        return Mono.just("Witaj, Mono!");
    }

    // Flux: Strumień, który może emitować 0 do N elementów
    public Flux<Integer> getFlux() {
        return Flux.range(1, 5); // Emituje liczby od 1 do 5
    }
}