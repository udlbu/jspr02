# ✅ Przepis na MongoDB z Docker Compose i Spring Boot

## 🔹 Kroki

- **1️⃣ Uruchom Docker**
    - Sprawdź, czy Docker działa:
      ```sh
      docker --version
      ```  

- **2️⃣ Utwórz `docker-compose.yml`** (Uruchamia MongoDB)
    - Konfiguruje kontener MongoDB
    - Otwiera port `27017`

- **3️⃣ Skonfiguruj `application.yml`**
    - Ustawia połączenie z MongoDB

- **4️⃣ Utwórz model `User`**
    - Reprezentuje użytkownika w MongoDB

- **5️⃣ Utwórz `UserRepository`**
    - Obsługuje operacje CRUD na użytkownikach

- **6️⃣ Utwórz `UserController`**
    - Udostępnia endpointy REST do zarządzania użytkownikami

- **7️⃣ Uruchom Docker Compose**
    - Włącz MongoDB w tle:
      ```sh
      docker-compose up -d
      ```  

- **8️⃣ Sprawdź API w przeglądarce lub Postmanie**
    - **Dodaj użytkownika:**
      ```
      curl -X POST "http://localhost:8080/users" -H "Content-Type: application/json" -d '{ "name": "Alice" }'

      ```  
    - **Pobierz wszystkich użytkowników:**
      ```
      GET http://localhost:8080/users
      ```  

- **9️⃣ Uruchom testy Spring Boot**
  ```sh
  ./mvnw test
