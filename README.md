# Przykłady Spring Expression Language (SpEL)


## Krok 1: Uruchomienie testów i zapoznanie się z projektem


**Upewnij się, że w IntelliJ aktywoany jest Lombok**

## Krok 2: 20 zadań SPeL na podstawie przykładów

1. **Wyrażenie typu (T)**: Użyj funkcji `T()` w celu obliczenia wartości sinusów kąta `45` stopni (przekształć kąt na radiany).

2. **Zmienna SpEL**: Zdefiniuj zmienną `a` równą `10` i zmienną `b` równą `20`. Następnie oblicz ich sumę za pomocą SpEL.

3. **Funkcja użytkownika**: Stwórz metodę w klasie, która zwraca imię użytkownika, a następnie użyj tej metody w wyrażeniu SPeL.

4. **Referencje do beanów**: Zdefiniuj bean `userBean`, który zawiera właściwość `name`. Następnie użyj SPeL do uzyskania tej wartości.

5. **Wyrażenie ternarne**: Użyj operatora ternarnego, aby sprawdzić, czy liczba jest parzysta, a wynik przypisz do zmiennej.

6. **Operator Elvis**: Zastosuj operator Elvis, aby sprawdzić, czy zmienna `name` jest null. Jeśli tak, zwróć wartość domyślną "Guest".

7. **Operator bezpiecznej nawigacji**: Użyj operatora bezpiecznej nawigacji, aby bezpiecznie uzyskać dostęp do elementu listy, nawet jeśli lista jest null.

8. **Projekcja kolekcji**: Utwórz listę z obiektami użytkowników i użyj projekcji w SPeL, aby wyciągnąć tylko imiona użytkowników.

9. **Selektor kolekcji**: Zdefiniuj listę liczb i użyj selektora w SPeL, aby znaleźć tylko liczby większe niż 10.

10. **Zmienna w mapie**: Zdefiniuj mapę z danymi użytkowników, gdzie kluczem jest identyfikator użytkownika, a wartością jego imię. Użyj SPeL, aby uzyskać imię użytkownika po jego identyfikatorze.

11. **Przykład użycia funkcji wbudowanej**: Użyj funkcji wbudowanej w SPeL do przekształcenia tekstu na wielkie litery.

12. **Konstruowanie tablicy**: Stwórz tablicę liczb w SPeL i użyj jej do obliczenia sumy jej elementów.

13. **Zastosowanie wyrażenia w konstruktorze**: Utwórz nowy obiekt `Person` w SPeL, używając konstruktora z dwoma parametrami: imieniem i wiekiem.

14. **Metoda statyczna**: Wywołaj metodę statyczną klasy `java.lang.Math`, aby obliczyć pierwiastek kwadratowy z liczby.

15. **Wywołanie metody na beanach**: Zdefiniuj bean `Calculator`, który ma metodę `add(int, int)`, i wywołaj ją w SPeL.

16. **Użycie właściwości obiektu**: Zdefiniuj klasę `Car` z właściwością `model` i uzyskaj wartość tej właściwości w SPeL.

17. **Zastosowanie funkcji w klasie**: Zdefiniuj funkcję `calculateAge()` w klasie `Person`, która oblicza wiek użytkownika na podstawie daty urodzenia, i użyj jej w SPeL.

18. **Referencja do obiektu w mapie**: Zdefiniuj mapę z obiektami użytkowników, a następnie uzyskaj dostęp do właściwości obiektu w mapie.

19. **Zastosowanie funkcji `replaceAll()`**: Zastosuj funkcję `replaceAll()` w SpEL, aby usunąć wszystkie cyfry z tekstu.

20. **Wyrażenie z operatorem `==`**: Sprawdź w SpEL, czy dwa obiekty są identyczne (używając operatora `==`) i zwróć wynik.
