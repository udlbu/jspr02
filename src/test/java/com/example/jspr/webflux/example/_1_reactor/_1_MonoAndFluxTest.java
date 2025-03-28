package com.example.jspr.webflux.example._1_reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class _1_MonoAndFluxTest {

    _1_MonoAndFlux monoAndFlux = new _1_MonoAndFlux();

    @Test
    public void testMono() {
        // Testowanie Mono, które emituje jeden element
        Mono<String> result = monoAndFlux.getMono();

        StepVerifier.create(result)
                .expectNext("Witaj, Mono!")  // Oczekujemy, że ten element zostanie wyemitowany
                .verifyComplete();  // Weryfikacja zakończenia strumienia Mono
    }

    @Test
    public void testFlux() {
        // Testowanie Flux, które emituje zakres wartości (od 1 do 5)
        Flux<Integer> result = monoAndFlux.getFlux();

        StepVerifier.create(result)
                .expectNext(1, 2, 3, 4, 5)  // Oczekujemy, że te wartości zostaną wyemitowane
                .verifyComplete();  // Weryfikacja zakończenia strumienia Flux
    }

}
