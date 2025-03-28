package com.example.jspr.webflux.example._1_reactor;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class _2_TransformingDataTest {

    _2_TransformingData transformingData = new _2_TransformingData();

    @Test
    public void testTransformMono() {
        // Testowanie transformacji Mono
        Mono<String> result = transformingData.transformMono();

        StepVerifier.create(result)
                .expectNext("Liczba podwojona: 10")  // Oczekujemy, że liczba 5 zostanie przemnożona przez 2
                .verifyComplete();  // Weryfikacja zakończenia
    }

    @Test
    public void testTransformWithFlatMap() {
        // Testowanie transformacji z użyciem flatMap
        Mono<String> result = transformingData.transformWithFlatMap();

        StepVerifier.create(result)
                .expectNext("Witaj w Reactor")  // Oczekujemy przekształconego ciągu tekstowego
                .verifyComplete();  // Weryfikacja zakończenia
    }

    @Test
    public void testTransformFlux() {
        // Testowanie transformacji Flux
        Flux<String> result = transformingData.transformFlux();

        StepVerifier.create(result)
                .expectNext("Liczba: 1", "Liczba: 2", "Liczba: 3")  // Oczekujemy zamiany liczb na tekst
                .verifyComplete();  // Weryfikacja zakończenia
    }

    @Test
    public void testFilterFlux() {
        // Testowanie filtrowania Flux
        Flux<Integer> result = transformingData.filterFlux();

        StepVerifier.create(result)
                .expectNext(2, 4)  // Oczekujemy tylko liczby parzyste
                .verifyComplete();  // Weryfikacja zakończenia
    }

}
