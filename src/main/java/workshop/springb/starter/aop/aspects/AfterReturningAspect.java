package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static workshop.springb.starter.aop.AdviceConstans.AFTER_RETURNING;

@Aspect
@Component
@Profile(AFTER_RETURNING)
public class AfterReturningAspect {

    @AfterReturning("workshop.springb.starter.aop.pointcuts.AppPointcuts.methodsInClassesAnnotatedWithLoggable()")
    public void logAdvice(JoinPoint joinPoint) {
        // Zaloguj profil i typ klasy obiektu (target), którego metoda ma ten aspekt
        System.out.println(AFTER_RETURNING + " " + joinPoint.getTarget().getClass().getSimpleName());
    }

}