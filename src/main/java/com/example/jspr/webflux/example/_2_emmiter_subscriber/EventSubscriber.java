package com.example.jspr.webflux.example._2_emmiter_subscriber;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class EventSubscriber extends BaseSubscriber<Integer> {

    // Metoda odbierająca wszystkie elementy
    @Override
    protected void hookOnNext(Integer value) {
        System.out.println("Otrzymano: " + value); // Wypisujemy każdy odebrany element
        request(1); // Prosimy o kolejny element
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Zakończono emisję!");
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.err.println("Wystąpił błąd: " + throwable.getMessage());
    }

    // Metoda odbierająca tylko 5 pierwszych elementów
    public void subscribeFirstFive(Flux<Integer> flux) {
        flux.take(5).subscribe(this); // Przerywamy subskrypcję po 5 elementach
    }

    // Metoda odbierająca wszystkie elementy
    public void subscribeAll(Flux<Integer> flux) {
        flux.subscribe(this); // Odbieramy wszystkie elementy
    }
}
