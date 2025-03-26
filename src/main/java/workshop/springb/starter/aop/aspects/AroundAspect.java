package workshop.springb.starter.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static workshop.springb.starter.aop.AdviceConstans.AROUND;


/*
    TODO 2 @Aspect oznacza klasę, w której będziemy definiowali logikę poboczną – niezwiązaną z domeną,
    ale istotną dla aplikacji, np. bezpieczeństwo, logowanie itp.

    W naszym przypadku będziemy wypisywać do konsoli jakiś tekst ツ
*/
@Aspect
@Component
@Profile(AROUND)
public class AroundAspect {
    static final String AROUND_BEFORE_MSG = "around before";
    static final String AROUND_AFTER_MSG = "around after";

    /*
        TODO 3 @Around, @Before, @After, @AfterReturning, @AfterThrowing
        ------------------------------------------------------------------------

        Te adnotacje oznaczają Advice – metody z dodatkowymi funkcjonalnościami,
        które wywołujemy razem z logiką domenową.

        @Around – dodatkowa logika przed i po metodzie
        @Before, @After – zgodnie z nazwą
        @AfterReturning – po metodzie, jeśli nie rzucono wyjątku
        @AfterThrowing – po metodzie, jeśli rzucono wyjątek

        W przykładzie logAdvice przyjmuje ProceedingJoinPoint.
        Wywołanie proceedingJoinPoint.proceed() uruchamia metodę,
        do której dodajemy dodatkową logikę.
        ------------------------------------------------------------------------
    */
    @Around("workshop.springb.starter.aop.pointcuts.AppPointcuts.methodsInServicePackage()")
    public Object logAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*
            TODO 5 @Around advice daje najwięcej możliwości, jako jedyna przyjmuje ProceedingJoinPoint


            @JoinPoint vs @ProceedingJoinPoint:
            - @JoinPoint to punkt, w którym AOP przechwytuje wywołanie metody, umożliwiając dostęp do informacji o metodzie,
            takich jak jej argumenty czy nazwa.
            - @ProceedingJoinPoint rozszerza @JoinPoint i dodatkowo umożliwia kontynuowanie wywołania metody
            (przy użyciu proceed()), co jest niezbędne dla @Around advice.

            Przejdźmy do TODO6
         */
        Object o;
        System.out.println(AROUND_BEFORE_MSG);
        o = proceedingJoinPoint.proceed();
        System.out.println(AROUND_AFTER_MSG);
        return o;
    }
}