package com.example.jspr.webflux.example._1_reactor;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public class _2_TransformingData {

    // Przykład 1: Użycie map() do transformacji danych (przekształcanie wartości)
    public Mono<String> transformMono() {
        return Mono.just(5)
                .map(number -> "Liczba podwojona: " + (number * 2)); // Mnożymy przez 2
    }

    // Przykład 2: Użycie flatMap() do przekształcenia Mono w inne Mono
    public Mono<String> transformWithFlatMap() {
        return Mono.just("Reactor")
                .flatMap(s -> Mono.just("Witaj w " + s)); // Zwracamy nowe Mono po przekształceniu
    }

    // Przykład 3: Użycie map() do transformacji Flux (zmiana typu elementów w Flux)
    public Flux<String> transformFlux() {
        return Flux.just(1, 2, 3)
                .map(i -> "Liczba: " + i); // Zamiana liczb na ciągi tekstowe
    }

    // Przykład 4: Użycie filter() do filtrowania danych w Flux (tylko liczby parzyste)
    public Flux<Integer> filterFlux() {
        return Flux.range(1, 5)
                .filter(i -> i % 2 == 0); // Wybieramy tylko liczby parzyste
    }
}
