# ✅ Zadanie 1: **Uruchomienie Docker Compose i Testy Spring Boot**

## Specyfikacja

- **Opis:** Uruchom docker-compose,  następnie uruchom testy Spring Boot, zapoznaj się z kodem i sprawdź działanie endpointów w aplikacji.

- **Kroki:**
    1. Uruchom docker-compose
    ```docker-compose up```
    2. Uruchom aplikację Spring Boot i upewnij się, że wszystkie kontenery są uruchomione przed testowaniem.
    3. Zapoznaj się z receptami w podpakietach: `mongodb`, `redis`, `sql`:
    4. Przejrzyj implementacje dla MongoDB, Redis i SQL oraz upewnij się, że aplikacja uruchamia odpowiednie bazy.
    5. Uruchom testy repozytoriów dla MongoDB, Redis i PostgreSQL, aby upewnić się, że połączenia z bazą i zapisy/odczyty działają prawidłowo.
    6. Przetestuj endpointy API w aplikacji Spring Boot. Sprawdź, czy wszystkie endpointy działają poprawnie.

---

# ✅ Zadanie 2: **Redis - Przechowywanie Prostych Danych**

## Specyfikacja

- **Opis:** Stwórz aplikację Spring Boot, która będzie przechowywać dane w Redisie, takie jak proste pary klucz-wartość (np. tekstowe dane). Aplikacja powinna udostępniać REST API, które pozwala na dodawanie nowych danych i pobieranie ich na podstawie klucza.

- **Wymagania:**
    1. Użyj Docker Compose do uruchomienia Redis w kontenerze.
    2. **Baza danych Redis:** uruchomiona na porcie `6370`.
    3. Stwórz model, który reprezentuje dane przechowywane w Redis (np. `DataModel` z polami `key` i `value`).
    4. Utwórz repozytorium, które będzie obsługiwało operacje zapisu i odczytu w Redis.
    5. Stwórz kontroler REST:
        - **POST /data/{key}** - przyjmuje dane w formacie JSON `{ "value": "some_value" }` i zapisuje je w Redis pod kluczem `{key}`.
        - **GET /data/{key}** - pobiera wartość przypisaną do klucza `{key}` z Redis i zwraca ją jako odpowiedź.
    6. Stwórz test repozytorium, który zapisuje i pobiera dane w Redis.

## API:
- **POST /data/{key}:**
    - Przyjmuje JSON z polem `value`, np.
      ```json
      { "value": "some_value" }
      ```
    - Zapisuje dane w Redis pod kluczem `{key}`.
    - Odpowiedź:
      ```json
      { "key": "exampleKey", "value": "some_value" }
      ```

- **GET /data/{key}:**
    - Pobiera dane z Redis na podstawie klucza `{key}`.
    - Odpowiedź:
      ```json
      { "value": "some_value" }
      ```

---

# ✅ Zadanie 3: **MongoDB - Przechowywanie Prostych Dokumentów**

## Specyfikacja

- **Opis:** Stwórz aplikację Spring Boot, która będzie przechowywać proste dokumenty w MongoDB. Aplikacja powinna udostępniać REST API, które pozwala na dodawanie nowych dokumentów i pobieranie ich na podstawie ID.

- **Wymagania:**
    1. Użyj Docker Compose do uruchomienia MongoDB w kontenerze.
    2. **Baza danych MongoDB:** uruchomiona na porcie `27010`.
    3. Stwórz model, który reprezentuje dokumenty przechowywane w MongoDB (np. `SimpleDocument` z polami `id` oraz `content`).
    4. Utwórz repozytorium, które będzie obsługiwało operacje zapisu i odczytu w MongoDB.
    5. Stwórz kontroler REST:
        - **POST /document** - przyjmuje dane w formacie JSON `{ "content": "some_content" }` i zapisuje je w MongoDB.
        - **GET /document/{id}** - pobiera dokument na podstawie ID z MongoDB i zwraca go jako odpowiedź.
    6. Stwórz test repozytorium, który zapisuje i pobiera dokument z MongoDB.

##  API:
- **POST /document:**
    - Przyjmuje JSON z polem `content`, np.
      ```json
      { "content": "some_content" }
      ```
    - Zapisuje dokument w MongoDB.
    - Odpowiedź:
      ```json
      { "id": "some_generated_id", "content": "some_content" }
      ```

- **GET /document/{id}:**
    - Pobiera dokument z MongoDB na podstawie ID.
    - Odpowiedź:
      ```json
      { "id": "some_generated_id", "content": "some_content" }
      ```