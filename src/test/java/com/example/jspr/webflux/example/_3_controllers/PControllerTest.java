package com.example.jspr.webflux.example._3_controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testMonoEndpoint() {
        webTestClient.get().uri("/api/programatic/mono")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello from Mono");
    }

    @Test
    public void testFluxEndpoint() {
        webTestClient.get().uri("/api/programatic/flux")
                .accept(org.springframework.http.MediaType.TEXT_EVENT_STREAM) // Ensure streaming
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)     // Obsługuje odpowiedź jako strumień
                .getResponseBody()              // Pobieramy strumień odpowiedzi
                .take(3)                     // Pobieramy tylko pierwsze 3 elementy
                .collectList()                  // Zbieramy je do listy
                .doOnNext(messages -> System.out.println("Otrzymane wiadomości: " + messages)) // Debugowanie
                .block();                       // Oczekujemy na zakończenie odpowiedzi
    }
}