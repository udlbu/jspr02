package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static workshop.springb.starter.aop.AdviceConstans.BEFORE;

/*
    TODO 4a: Zmień klasę na aspekt, który działa tylko w profilu 'before'.
 */
@Aspect
@Component
@Profile(BEFORE)
public class BeforeAspect {

    /*
       TODO 4b: Advice: before, z pointcut'em wskazującym na metody w klasie
       workshop.springb.starter.service i jej podpakietach.

       Zaloguj profil do konsoli.
    */

    @Before("workshop.springb.starter.aop.pointcuts.AppPointcuts.methodsInServicePackageAndSubpackages()")
    public void logAdvice() {
        // Zaloguj profil przed wykonaniem metody
        System.out.println(BEFORE);
    }

}
// TODO 4c: Utwórz test (klasa testowa już czeka) ツ