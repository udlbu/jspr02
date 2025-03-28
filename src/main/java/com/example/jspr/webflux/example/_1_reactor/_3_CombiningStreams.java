package com.example.jspr.webflux.example._1_reactor;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public class _3_CombiningStreams {

    // Przykład 1: Łączenie Mono z Mono za pomocą zip()
    public Mono<String> combineMonoWithMono() {
        Mono<String> mono1 = Mono.just("Hello");
        Mono<String> mono2 = Mono.just("World");
        return Mono.zip(mono1, mono2, (s1, s2) -> s1 + " " + s2); // Łączymy oba Mono
    }

    // Przykład 2: Łączenie Flux z Flux za pomocą zip()
    public Flux<String> combineFluxWithFlux() {
        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<String> flux2 = Flux.just("1", "2", "3");
        return Flux.zip(flux1, flux2, (s1, s2) -> s1 + s2); // Łączymy elementy obu Flux
    }

    // Przykład 3: Łączenie Mono z Flux za pomocą flatMap()
    public Flux<String> combineMonoWithFlux() {
        Mono<String> mono = Mono.just("Hello");
        Flux<String> flux = Flux.just("Reactor", "World");
        return mono.flatMapMany(s -> flux.map(f -> s + " " + f)); // Łączymy Mono z Flux
    }

    // Przykład 4: Łączenie Flux za pomocą merge()
    public Flux<String> mergeFlux() {
        Flux<String> flux1 = Flux.just("A", "B");
        Flux<String> flux2 = Flux.just("1", "2");
        return Flux.merge(flux1, flux2); // Łączenie dwóch Flux w jeden
    }

    // Przykład 5: Łączenie Mono z Flux za pomocą concat()
    public Flux<String> concatMonoWithFlux() {
        Mono<String> mono = Mono.just("Start");
        Flux<String> flux = Flux.just("One", "Two", "Three");
        return Flux.concat(mono, flux); // Łączenie Mono z Flux, Mono wyemitowane najpierw
    }
}

