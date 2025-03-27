# ✅ Przepis na Redis z Docker Compose i Spring Boot

## 🔹 Kroki

- **1️⃣ Uruchom Docker**
    - Sprawdź, czy Docker działa:
      ```sh
      docker --version
      ```  

- **2️⃣ Utwórz `docker-compose.yml`** (Uruchamia Redis)
    - Konfiguruje kontener Redis
    - Otwiera port `6379`

- **3️⃣ Skonfiguruj `application.yml`**
    - Ustawia połączenie z Redisem

- **4️⃣ Utwórz model `UserSession`**
    - Reprezentuje sesję użytkownika w Redis

- **5️⃣ Utwórz `UserSessionRepository`**
    - Obsługuje zapisywanie i pobieranie sesji

- **6️⃣ Utwórz `UserSessionController`**
    - Udostępnia endpointy REST do zarządzania sesjami

- **7️⃣ Uruchom Docker Compose**
    - Włącz Redis w tle:
      ```sh
      docker-compose up -d
      ```  

- **8️⃣ Sprawdź API w przeglądarce lub Postmanie**
    - **Utwórz sesję:**
      ```
      curl -X POST "http://localhost:8080/session/123?data=testData"

      ```  
    - **Pobierz sesję:**
      ```
      GET http://localhost:8080/session/123
      ```  

- **9️⃣ Uruchom testy Spring Boot**
  ```sh
  ./mvnw test

** Wyczyszczenie REDISs **

```
docker exec -it redis_test redis-cli FLUSHALL
```