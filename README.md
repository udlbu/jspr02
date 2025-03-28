
# WebFlux - Reactive Programming in Spring

## Opis projektu

Projekt przedstawia przykłady z zakresu programowania reaktywnego z użyciem Spring WebFlux. Zawiera różne klasy pokazujące podstawowe koncepcje i operacje strumieniowe z wykorzystaniem Mono i Flux. Projekt obejmuje także testy jednostkowe oraz testy integracyjne API.

### Spis treści:
1. [Wstęp do WebFlux](#wstęp-do-webflux)
2. [Strumienie Mono i Flux](#strumienie-mono-i-flux)
3. [Transformacje danych](#transformacje-danych)
4. [Łączenie strumieni](#łączenie-strumieni)
5. [Emitery i subskrypcje](#emitery-i-subskrypcje)
6. [Backpressure](#backpressure)
7. [Różnice między WebFlux a MVC](#różnice-między-webflux-a-mvc)
8. [Adnotacje i Programistyczne Endpointy](#adnotacje-i-programistyczne-endpointy)
9. [Słowniczek](#słowniczek)
10. [Ćwiczenia](#ćwiczenia)

---

## Wstęp do WebFlux

WebFlux to framework oparty na programowaniu reaktywnym, który jest częścią ekosystemu Spring. WebFlux pozwala na tworzenie aplikacji opartych na strumieniach danych, które mogą emitować wartości asynchronicznie. W porównaniu do tradycyjnego Spring MVC, WebFlux używa podejścia reaktywnego, które daje lepsze możliwości skalowania i obsługi dużych obciążeń.

- WebFlux korzysta z Reactor'a jako swojej domyślnej implementacji programowania reaktywnego.
- Reactor dostarcza podstawowe konstrukcje, takie jak Mono i Flux, które WebFlux wykorzystuje do obsługi strumieni danych w sposób asynchroniczny i nieblokujący. 
- Dzięki temu WebFlux umożliwia skalowalne przetwarzanie żądań HTTP oraz integrację z innymi reaktywnymi komponentami Springa, np. Spring Data R2DBC (dla baz SQL) czy Spring Data MongoDB (dla NoSQL).
## Strumienie Mono i Flux

- **Mono**: Reprezentuje strumień, który może emitować 0 lub 1 element.
- **Flux**: Reprezentuje strumień, który może emitować od 0 do N elementów.

### Przykład Mono
```java
public Mono<String> getMono() {
    return Mono.just("Witaj, Mono!");
}
```

### Przykład Flux
```java
public Flux<Integer> getFlux() {
    return Flux.range(1, 5);
}
```

## Transformacje danych

WebFlux pozwala na przekształcanie danych w strumieniach Mono i Flux. Przykłady takich operacji to:
- **map()**: Transformacja elementów.
- **flatMap()**: Zastosowanie transformacji do strumienia Mono lub Flux.
- **filter()**: Filtrowanie elementów.

### Przykład map() w Mono
```java
public Mono<String> transformMono() {
    return Mono.just(5)
        .map(number -> "Liczba podwojona: " + (number * 2));
}
```

### Przykład flatMap() w Flux
```java
public Mono<String> transformWithFlatMap() {
    return Mono.just("Reactor")
        .flatMap(s -> Mono.just("Witaj w " + s));
}
```

## Łączenie strumieni

Możemy łączyć Mono i Flux na różne sposoby:
- **zip()**: Łączenie dwóch strumieni Mono lub Flux w jeden.
- **merge()**: Łączenie wielu strumieni Flux.
- **concat()**: Łączenie Mono z Flux, gdzie Mono emituje wartość przed Flux.

### Przykład zip()
```java
public Mono<String> combineMonoWithMono() {
    Mono<String> mono1 = Mono.just("Hello");
    Mono<String> mono2 = Mono.just("World");
    return Mono.zip(mono1, mono2, (s1, s2) -> s1 + " " + s2);
}
```

## Emitery i subskrypcje

### Emitery
Emitery w WebFlux generują strumienie, które następnie mogą być subskrybowane przez odbiorców. Przykład prostego emitera, który generuje liczby od 1 do 20:
```java
public Flux<Integer> emitEvents() {
    return Flux.range(1, 20);
}
```

### Subskrypcje
Subskrybenci odbierają elementy z emitera i przetwarzają je. Przykład subskrypcji odbierającej tylko 5 pierwszych elementów:
```java
public void subscribeFirstFive(Flux<Integer> flux) {
    flux.take(5).subscribe(this); // Odbieramy tylko pierwsze 5 elementów
}
```

## Backpressure

**Backpressure** jest techniką zarządzania przepływem danych w strumieniach reaktywnych, szczególnie przy dużych ilościach danych, aby zapobiec przeciążeniu systemu.

### Przykład bez backpressure:
```java
// Emitowanie danych bez kontroli przepływu
Flux.range(1, 1000).subscribe(System.out::println);
```

### Przykład z backpressure:
```java
// Subskrybent żąda tylko 10 elementów, kontrolując przepływ
flux.take(10).subscribe(System.out::println);
```

WebFlux obsługuje backpressure zarówno w Mono, jak i Flux, zapewniając kontrolę nad ilością danych, które są przetwarzane w danym czasie.

## **WebFlux vs. Spring MVC – Kluczowe Różnice**

#### **Model Przetwarzania:**
- **WebFlux**: Reaktywne, asynchroniczne przetwarzanie danych, obsługa backpressure.
- **Spring MVC**: Blokujące, synchroniczne operacje I/O.

#### **Obsługa Żądań:**
- **WebFlux**: Strumieniowa obsługa żądań i odpowiedzi, możliwa obsługa wielu żądań jednocześnie bez blokowania wątków.
- **Spring MVC**: Każde żądanie blokuje wątek do momentu zakończenia obsługi.

#### **Serwery Aplikacyjne (Domyślne):**
- **WebFlux**: **Netty** (domyślnie), ale można używać **Tomcat, Jetty lub Undertow** w trybie reaktywnym.
- **Spring MVC**: **Tomcat** (domyślnie), ale można używać także Jetty lub Undertow (w trybie blokującym).

#### **Obsługa Baz Danych:**
- **WebFlux**: Przystosowany do reaktywnych baz danych (np. MongoDB, R2DBC).
- **Spring MVC**: Głównie używa JDBC i blokujących baz danych (np. PostgreSQL, MySQL).

#### **Zastosowania:**
- **WebFlux**: Aplikacje o wysokiej skalowalności, przetwarzanie dużych ilości danych w czasie rzeczywistym.
- **Spring MVC**: Tradycyjne aplikacje webowe, dobrze pasuje do aplikacji monolitycznych.

> Mimo że **Tomcat** jest domyślny w Spring MVC, można go także używać z WebFlux, ale wtedy działa w trybie klasycznym, blokującym (nie w pełni reaktywnie).


## Przykłady Endpoint'ów w WebFLux

### Adnotacje

```java
@RestController
@RequestMapping("/api/annotated")
public class ReactiveController {

    @GetMapping("/mono")
    public Mono<String> getMono() {
        return Mono.just("Hello from Mono");
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFlux() {
        return Flux.just("Hello", "from", "Flux");
    }
}

```

### Endpointy programistyczne

```java
@Bean
public RouterFunction<ServerResponse> programmaticRoutes() {
    return route(GET("/api/programatic/mono"),
            request -> ServerResponse.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(Mono.just("Hello from Mono"), String.class))
            .andRoute(GET("/api/programatic/flux"),
                    request -> ServerResponse.ok()
                            .contentType(MediaType.TEXT_EVENT_STREAM)
                            .body(Flux.just("Hello", "from", "Flux"), String.class));
}
```

## Słowniczek

- **Mono**: Strumień, który emituje 0 lub 1 element.
- **Flux**: Strumień, który emituje 0 lub więcej elementów.
- **map()**: Operacja, która transformuje elementy strumienia.
- **flatMap()**: Operacja, która przekształca jeden element w nowy strumień.
- **filter()**: Operacja, która filtruje elementy strumienia na podstawie warunku.
- **zip()**: Łączenie dwóch strumieni w jeden, parując ich elementy.
- **merge()**: Łączenie dwóch strumieni w jeden, emitując elementy w czasie rzeczywistym.
- **concat()**: Łączenie strumieni, emitując elementy w kolejności.
- **BaseSubscriber**: Klasa służąca do subskrypcji i odbierania elementów w strumieniu.
- **Reactor**: Biblioteka do programowania reaktywnego w Javie, stanowiąca część ekosystemu Spring, oferująca implementację wzorców reaktywnych z użyciem `Mono` i `Flux`. Reactor obsługuje asynchroniczność, backpressure i strumienie danych w sposób reaktywny.
- **backpressure**: Techniką zarządzania przepływem danych w reaktywnych strumieniach, zapewniającą kontrolę nad ilością przetwarzanych danych.

---


# Ćwiczenia

## W poniższych ćwiczeniach dotykamy wszystkich zagadnień z całego szkolenia

### 1. **Endpoint z Redis i logowanie AOP z SPEL**

- **Opis**:
    - Utwórz endpoint WebFlux, który zwraca dane użytkownika z bazy Redis w postaci strumienia `Mono`.
    - Skorzystaj z AOP, aby dodać logowanie czasu wykonania zapytania.
    - Wykorzystaj SPEL do dynamicznego ustalania poziomu logowania (np. `log.level` w konfiguracji).

- **Elementy**:
    - Tworzenie endpointu WebFlux dla Redis.
    - Wykorzystanie `Mono` do pobierania jednego elementu.
    - Zastosowanie AOP do logowania.
    - Użycie SPEL do dynamicznej zmiany poziomu logowania.

---

### 2. **Endpoint z MongoDB i logowanie AOP z obsługą błędów**

- **Opis**:
    - Zaimplementuj endpoint WebFlux, który zwraca listę produktów z bazy MongoDB w postaci strumienia `Flux`.
    - Dodaj AOP do logowania dostępu do bazy danych oraz do obsługi błędów.
    - Skorzystaj z SPEL do określenia, czy zapytanie powinno być zapisane w logach, bazując na jego typie.

- **Elementy**:
    - Tworzenie endpointu WebFlux dla MongoDB.
    - Zastosowanie `Flux` do pobierania wielu elementów.
    - Implementacja logowania w AOP.
    - Obsługa błędów w WebFlux.
    - Użycie SPEL do kontrolowania logowania zapytań.

---

### 3. **Łączenie danych z Redis i MongoDB z monitorowaniem AOP**

- **Opis**:
    - Utwórz endpoint WebFlux, który łączy dane z Redis i MongoDB w jeden strumień `Flux`.
    - Zastosuj AOP do monitorowania liczby zapytań do obu baz.
    - Weryfikuj dostęp do danych na podstawie ról użytkownika, wykorzystując SPEL do autoryzacji.

- **Elementy**:
    - Tworzenie endpointu WebFlux łączącego Redis i MongoDB.
    - Wykorzystanie `Flux` do łączenia strumieni danych.
    - AOP do monitorowania zapytań.
    - Wykorzystanie SPEL do autoryzacji na podstawie roli użytkownika.

---

### 4. **Backpressure w Redis z dynamicznym zarządzaniem AOP i SPEL**

- **Opis**:
    - Utwórz endpoint WebFlux, który pobiera dane o użytkownikach z Redis, zapewniając kontrolę nad przepływem danych (backpressure).
    - Dodaj AOP do monitorowania liczby przetworzonych elementów.
    - Użyj SPEL do dynamicznego ustalania limitu backpressure w zależności od ustawień konfiguracyjnych.

- **Elementy**:
    - Implementacja backpressure w WebFlux.
    - Pobieranie danych z Redis z użyciem `Flux`.
    - AOP do monitorowania przepływu danych.
    - Użycie SPEL do dynamicznej zmiany limitów backpressure.

---

### 5. **Przekształcanie danych z MongoDB z logowaniem AOP i SPEL**

- **Opis**:
    - Utwórz endpoint WebFlux, który pobiera dane o produktach z MongoDB i przekształca je na format JSON.
    - Zastosuj operację `map()` do przekształcenia danych w strumieniu `Flux`.
    - Dodaj AOP do logowania przekształconych danych, używając SPEL do warunkowego logowania w zależności od statusu produktu.

- **Elementy**:
    - Tworzenie endpointu WebFlux do pobierania danych z MongoDB.
    - Użycie `Flux` i `map()` do przekształcania danych.
    - Implementacja AOP do logowania przekształconych danych.
    - Zastosowanie SPEL do warunkowego logowania w zależności od statusu produktu.
