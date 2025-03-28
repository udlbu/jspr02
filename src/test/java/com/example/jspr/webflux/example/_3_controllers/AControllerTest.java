package com.example.jspr.webflux.example._3_controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testMonoEndpoint() {
        webTestClient.get().uri("/api/annotations/mono")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello from Mono");
    }

    @Test
    public void testFluxEndpoint() {
        webTestClient.get().uri("/api/annotations/flux")
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)     // Obsługa odpowiedzi jako strumienia
                .getResponseBody()              // Pobranie strumienia odpowiedzi
                .take(3)                     // Pobranie tylko pierwszych 3 elementów
                .collectList()                  // Zebranie ich do listy
                .doOnNext(messages -> System.out.println("Otrzymane wiadomości: " + messages)) // Debugowanie
                .block();                       // Oczekiwanie na zakończenie odpowiedzi
    }
}