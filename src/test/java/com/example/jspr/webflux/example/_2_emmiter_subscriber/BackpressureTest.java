package com.example.jspr.webflux.example._2_emmiter_subscriber;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;


public class BackpressureTest {

    @Test
    public void testSubscribeAll() {
        EventEmitter eventEmitter = new EventEmitter();
        EventSubscriber eventSubscriber = new EventSubscriber();

        // Subskrybujemy i odbieramy wszystkie elementy
        eventSubscriber.subscribeAll(eventEmitter.emitEvents());

        // Sprawdzamy, czy strumień zawiera wszystkie elementy od 1 do 20
        StepVerifier.create(eventEmitter.emitEvents())
                .expectNextCount(20)                              // Oczekujemy, że będzie 20 elementów
                .verifyComplete();                                  // Potwierdzamy zakończenie emisji
    }

    @Test
    public void testSubscribeFirstFive() {
        EventEmitter eventEmitter = new EventEmitter();
        EventSubscriber eventSubscriber = new EventSubscriber();

        // Subskrybujemy tylko pierwsze 5 elementów
        eventSubscriber.subscribeFirstFive(eventEmitter.emitEvents());

        // Weryfikujemy tylko 5 elementów
        StepVerifier.create(eventEmitter.emitEvents().take(5))  // Używamy 'take(5)' na strumieniu
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }
}