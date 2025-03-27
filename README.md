# Spring Events - README

## Wprowadzenie

Spring Framework zapewnia mechanizm zdarzeń (events), który umożliwia komunikację między komponentami aplikacji w sposób luźno powiązany. Istnieją wbudowane zdarzenia Springa, takie jak `ContextRefreshedEvent`, oraz możliwość tworzenia własnych zdarzeń (custom events), które mogą służyć do propagacji informacji w aplikacji.

## Przypadki użycia eventów:

1. **Komunikacja między komponentami** – luźne powiązanie między różnymi częściami aplikacji.
2. **Obsługa zdarzeń domenowych** – np. tworzenie użytkownika i wysyłanie powitalnego e-maila.
3. **Asynchroniczne przetwarzanie** – obsługa zdarzeń w tle bez blokowania wątku głównego.
4. **Rozszerzalność aplikacji** – np. dodanie własnych hooków do istniejącej aplikacji.
5. **Obsługa cyklu życia aplikacji** – zdarzenia Spring, np. `ContextRefreshedEvent`, `ContextClosedEvent`.
6. **Audyt i logowanie** – rejestrowanie zdarzeń w systemie dla celów monitoringu.
7. **Obsługa zdarzeń integracyjnych** – przekazywanie komunikatów między modułami lub mikroserwisami.


## Jak utworzyć Custom Event w Springu - krok po kroku:

1. **Stwórz klasę zdarzenia (`CustomEvent`)**, dziedziczącą po `ApplicationEvent`.
2. **Stwórz klasę nasłuchującą (`CustomListener`)**, implementującą `ApplicationListener<CustomEvent>`.
3. **Stwórz klasę publikującą (`CustomPublisher`)**, wstrzykującą `ApplicationEventPublisher`.
    - **(3a) Wersja synchroniczna** – eventy są obsługiwane w tym samym wątku.
    - **(3b) Wersja asynchroniczna** – dodaj `ApplicationEventMulticaster` z `SimpleAsyncTaskExecutor`.
4. **Przetestuj działanie eventów. ** - zobacz gotowy test z TODO i przeczytaj wyjaśnienie w podpunkcie: Jak asynchroniczna konfiguracja wpływa na eventy w Springu

## TODO - Kroki implementacji Custom Event w Spring Boot

### 1. Utworzenie szkieletu aplikacji - utwórz pakiet 'com.example.jspr.event', w nim poniższe poniższe klasy:


- `MainConfig` - konfiguracja aplikacji
- `CustomEvent` - klasa zdarzenia
- `CustomListener` - klasa nasłuchująca
- `CustomPublisher` - klasa publikująca zdarzenia
- `SpringEventListener` - słuchacz zdarzeń Springa  


### 2. Klasa konfiguracyjne - AsyncConfig

```java
@Configuration
@ComponentScan(basePackages = "com.example.jspr.event")
public class AsyncConfig {
    
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}
```
### Własne zdarzenie i jego publikacja


```java
public class CustomEvent extends ApplicationEvent {
    private final String msg;

    public CustomEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
```

```java
@Component
public class CustomPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(String eventMsg) {
        System.out.println("Publishing event from thread: " + Thread.currentThread().getName());
        applicationEventPublisher.publishEvent(new CustomEvent(this, eventMsg));
    }
}
```

### Nasłuchiwanie zdarzenia 'pudełkowego' - ContextRefreshedEvent

```java
@Component
public class SpringEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Application Context has been refreshed");
    }
}
```

### Nasłuchiwanie własnego zdarzenie - CustomEvent

```java
@Component
public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        System.out.println("Received CustomEvent: " + customEvent.getMsg());
        System.out.println("Processing event on thread: " + Thread.currentThread().getName());
    }
}
```

--

## Jak asynchroniczna konfiguracja wpływa na eventy w Springu

W Springu, asynchroniczne eventy są obsługiwane za pomocą `ApplicationEventMulticaster` i `TaskExecutor`. Kiedy event jest publikowany, domyślnie Spring go przetwarza w tym samym wątku, w którym został wyemitowany. Jednak w przypadku użycia asynchronicznego `TaskExecutor`, eventy są przekazywane do osobnych wątków, aby mogły być obsługiwane równolegle. To może zwiększyć wydajność, ale wprowadza opóźnienia w ich przetwarzaniu.

## Dlaczego test nie przechodzi bez Thread.sleep() lub CountDownLatch

### Multicaster z asynchronicznym SimpleAsyncTaskExecutor:
Dzięki asynchronicznej konfiguracji, eventy są przetwarzane w osobnych wątkach. Gdy test sprawdza wynik przed zakończeniem przetwarzania eventu, event nie został jeszcze obsłużony, co skutkuje brakiem oczekiwanego komunikatu w konsoli.

### Problem z synchronizacją:
Test bez dodatkowego mechanizmu synchronizacji (np. `Thread.sleep()` lub `CountDownLatch`) nie daje wystarczająco dużo czasu, aby asynchroniczny wątek przetworzył zdarzenie. W wyniku tego test nie "widzi" komunikatu w momencie, gdy sprawdza strumień wyjściowy.

## Rozwiązania:

- **Thread.sleep()**: Wprowadza opóźnienie, które daje czas na zakończenie przetwarzania w tle, ale jest to mniej elegancka metoda.
- **CountDownLatch**: Synchronizuje główny wątek testu z asynchronicznym wątkiem, zapewniając, że test będzie czekał na zakończenie przetwarzania eventu, zanim dokona asercji.

--

## Eventy i transakcje

Domyślnie eventy są publikowane po zakończeniu transakcji (czyli po jej zatwierdzeniu lub wycofaniu). Jeśli wydarzenie jest publikowane wewnątrz transakcji, może dojść do nieoczekiwanych sytuacji, zwłaszcza w przypadku niepowodzenia transakcji.

```java
@Transactional
public void updateDataAndPublishEvent() {
    // Aktualizacja danych w bazie
    dataRepository.update(...);

    // Publikacja eventu
    applicationEventPublisher.publishEvent(new CustomEvent(this, "Data updated"));
}

```

### Problemy z powyższą konfiguracją:

Jeśli **transakcja się nie powiedzie** (np. poprzez wyjątek w dataRepository.update())
   - cała transakcja (w tym aktualizacja danych) zostanie wycofana, ale event zostanie i tak opublikowany.

Jeśli **transakcja się powiedzie, ale później coś pójdzie nie tak z przetwarzaniem eventu** 
   - (np. listener spowoduje wyjątek), event nadal może zostać uznany za opublikowany, mimo że proces aktualizacji nie został zakończony pomyślnie.


### Proponowane dwa podejścia:

1. **Publikacja eventów po zakończeniu transakcji (po commit):**
      - Użyj `@TransactionalEventListener` z `TransactionPhase.AFTER_COMMIT`:

```java
   @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
   public void handleEvent(CustomEvent event) {
       // Obsługa eventu po zatwierdzeniu transakcji
   }
```

2. **Zarządzanie transakcjami wewnątrz listenerów:**
   - Jeśli eventy muszą być publikowane wewnątrz transakcji, upewnij się, że nie wpływają na wynik transakcji (np. dodanie rollbacków w listenerach).