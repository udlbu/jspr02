package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static workshop.springb.starter.aop.AdviceConstans.AFTER;


@Aspect
@Profile(AFTER)
public class AfterAspect {

    @Before("workshop.springb.starter.aop.pointcuts.AppPointcuts.methodsStartWithGr()")
    public void logAdvice(JoinPoint joinPoint) {
        // Zaloguj profil i rodzaj join point (wykonanie metody) po wykonaniu advice
        System.out.println(AFTER + " " + joinPoint.getKind());
    }

}
