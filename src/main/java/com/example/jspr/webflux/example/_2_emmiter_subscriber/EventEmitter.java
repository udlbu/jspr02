package com.example.jspr.webflux.example._2_emmiter_subscriber;

import reactor.core.publisher.Flux;

public class EventEmitter {

    // Emitowanie 20 elementów w strumieniu
    public Flux<Integer> emitEvents() {
        return Flux.range(1, 20); // Generujemy 20 liczb od 1 do 20
    }
}

