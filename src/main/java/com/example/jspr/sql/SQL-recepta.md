# ✅ Przepis na SQL (PostgreSQL) z Docker Compose i Spring Boot

## 🔹 Kroki

- **1️⃣ Utwórz `docker-compose.yml`** (Uruchamia PostgreSQL)
    - Konfiguruje kontener PostgreSQL
    - Montuje `init.sql` do wstępnego załadowania danych

- **2️⃣ Utwórz `init.sql`** (Inicjalizacja danych)
    - Tworzy tabelę `users`
    - Wstawia dane testowe (`Alice`, `Bob`)

- **3️⃣ Skonfiguruj `application.yml`**
    - Ustawia połączenie PostgreSQL zgodne z Dockerem

- **4️⃣ Utwórz encję `User`**
    - Definiuje pola `id` i `name`

- **5️⃣ Utwórz `UserRepository`**
    - Rozszerza `JpaRepository<User, Long>`

- **6️⃣ Utwórz `UserController`**
    - Udostępnia endpoint `/users` do pobierania użytkowników

- **7️⃣ Utwórz `UserRepositoryDockerTest`**
    - Testuje połączenie z bazą danych w PostgreSQL na Dockerze
    - Wymaga uruchomionego Dockera

- **8️⃣ Uruchom Docker Compose**
    - Włącz PostgreSQL w tle:
      ```sh
      docker-compose up -d
      ```  

- **9️⃣ Sprawdź API w przeglądarce lub Postmanie**
    - **Pobierz wszystkich użytkowników:**
      ```
      curl -X GET "http://localhost:8080/users"

      ```  

- **🔟 Uruchom testy Spring Boot**
  ```sh
  ./mvnw test
