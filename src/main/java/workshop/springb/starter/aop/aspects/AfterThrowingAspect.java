package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAspect {

    /*
       TODO 3b: Advice: after throwing, z pointcut'em wskazującym na metody w klasie GreetSubservice,
       (Trzeba utworzyć pointcut)
    */
    public void logAdvice(JoinPoint joinPoint) {
        // Zaloguj profil i liczbę argumentów metody joinPoint
    }

}
// TODO 3c: upewnij się, że test przechodzi (klasa testowa już czeka) ツ