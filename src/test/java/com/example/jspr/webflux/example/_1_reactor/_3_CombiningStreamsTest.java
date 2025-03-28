package com.example.jspr.webflux.example._1_reactor;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class _3_CombiningStreamsTest {

    _3_CombiningStreams combiningStreams = new _3_CombiningStreams();

    @Test
    public void testCombineMonoWithMono() {
        // Testowanie łączenia Mono z Mono
        Mono<String> result = combiningStreams.combineMonoWithMono();

        StepVerifier.create(result)
                .expectNext("Hello World")  // Oczekujemy połączonego ciągu tekstowego
                .verifyComplete();  // Weryfikacja zakończenia
    }

    @Test
    public void testCombineFluxWithFlux() {
        // Testowanie łączenia Flux z Flux
        Flux<String> result = combiningStreams.combineFluxWithFlux();

        StepVerifier.create(result)
                .expectNext("A1", "B2", "C3")  // Oczekujemy połączonych elementów
                .verifyComplete();  // Weryfikacja zakończenia
    }

    @Test
    public void testCombineMonoWithFlux() {
        // Testowanie łączenia Mono z Flux
        Flux<String> result = combiningStreams.combineMonoWithFlux();

        StepVerifier.create(result)
                .expectNext("Hello Reactor", "Hello World")  // Oczekujemy połączonych elementów
                .verifyComplete();  // Weryfikacja zakończenia
    }

    @Test
    public void testMergeFlux() {
        // Testowanie łączenia Flux za pomocą merge
        Flux<String> result = combiningStreams.mergeFlux();

        StepVerifier.create(result)
                .expectNext("A", "B", "1", "2")  // Oczekujemy elementów z obu Flux
                .verifyComplete();  // Weryfikacja zakończenia
    }

    @Test
    public void testConcatMonoWithFlux() {
        // Testowanie łączenia Mono z Flux za pomocą concat
        Flux<String> result = combiningStreams.concatMonoWithFlux();

        StepVerifier.create(result)
                .expectNext("Start", "One", "Two", "Three")  // Oczekujemy Mono na początku, a potem Flux
                .verifyComplete();  // Weryfikacja zakończenia
    }
}
