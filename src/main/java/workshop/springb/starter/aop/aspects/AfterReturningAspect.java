package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.JoinPoint;

/*
    TODO 2a: Zmień klasę na aspekt, który działa tylko w profilu 'after_returning'.
 */
public class AfterReturningAspect {

    /*
         TODO 2b: Advice: after returning, z pointcut'em wskazującym na metody w klasach z adnotacją
         workshop.springb.starter.aop.anotations.@Loggable - w AppPointcuts jest gotowy pointcut do użycia
    */
    public void logAdvice(JoinPoint joinPoint) {
        // Zaloguj profil i typ klasy obiektu (target), którego metoda ma ten aspekt
    }

}

// TODO 2c: upewnij się, że test przechodzi (klasa testowa już czeka) ツ
