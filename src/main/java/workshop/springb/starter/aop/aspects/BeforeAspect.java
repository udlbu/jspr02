package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static workshop.springb.starter.aop.AdviceConstans.BEFORE;


@Aspect
@Component
@Profile(BEFORE)
public class BeforeAspect {


    @Before("workshop.springb.starter.aop.pointcuts.AppPointcuts.methodsInServicePackageAndSubpackages()")
    public void logAdvice() {
        // Zaloguj profil przed wykonaniem metody
        System.out.println(BEFORE);
    }

}