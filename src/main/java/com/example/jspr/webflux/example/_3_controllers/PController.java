package com.example.jspr.webflux.example._3_controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;  // Import predykatu dla GET
import static org.springframework.web.reactive.function.server.RouterFunctions.route;  // Import metody do tworzenia tras

@Configuration  // Definiuje konfigurację aplikacji
public class PController {

    @Bean
    public RouterFunction<ServerResponse> programmaticRoutes() {
        return route(GET("/api/programatic/mono"),
                request -> ServerResponse.ok()                             // Odpowiedź OK
                        .contentType(MediaType.TEXT_PLAIN)                              // Określenie typu odpowiedzi jako plain text
                        .body(Mono.just("Hello from Mono").log(), String.class))   // Zwrócenie Mono z tekstem

                .andRoute(GET("/api/programatic/flux"),
                        request -> ServerResponse.ok()                     // Odpowiedź OK
                                .contentType(MediaType.TEXT_EVENT_STREAM)               // Określenie typu odpowiedzi jako event stream
                                .body(Flux.just("Hello", "from", "Flux")         // Zwrócenie Flux z kilkoma elementami
                                        .delayElements(Duration.ofSeconds(1))           // Dodanie opóźnienia 1 sekundy między elementami
                                        .log(), String.class));                         // Logowanie procesu
    }
}
