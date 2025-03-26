package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static workshop.springb.starter.aop.AdviceConstans.AFTER_RETURNING;

/*
    TODO 2a: Zmień klasę na aspekt, który działa tylko w profilu 'after_returning'.
 */
@Aspect
@Component
@Profile(AFTER_RETURNING)
public class AfterReturningAspect {

    /*
         TODO 2b: Advice: after returning, z pointcut'em wskazującym na metody w klasach z adnotacją
         workshop.springb.starter.aop.anotations.@Loggable.
    */
    @AfterReturning("workshop.springb.starter.aop.pointcuts.AppPointcuts.methodsInClassesAnnotatedWithLoggable()")
    public void logAdvice(JoinPoint joinPoint) {
        // Zaloguj profil i typ klasy obiektu (target), którego metoda ma ten aspekt
        System.out.println(AFTER_RETURNING + " " + joinPoint.getTarget().getClass().getSimpleName());
    }

}

// TODO 2c: Utwórz test (klasa testowa już czeka) ツ
