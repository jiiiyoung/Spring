package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP : Aspect Oriented Programming
// 공통관심사항(Cross-cutting concern) VS 핵심 관심사항(Core concern) 분리
// 시간측정, 로그 찍기 등은 공통관심사항이다. 따라서 한번에 모아서 다양한 곳에 적용할 수 있도록 AOP로 구현
@Aspect
@Component // 스프링빈에서 직접 등록함(AOP기능을 더 인지하기 쉽게 하기 위해서)
public class TimeTraceAop {

    // @Around("execution(* hello.hello_spring..*(..)) && !target(hello.hello_spring.SpringConfig)") // 직접 Bean을 등록할때, 순환참조가 발생하지 않기 위해 SpringConfig를 제외시켜야 한다.
    // hello.hello_spring 하위에 모두 적용해 라고 표시한것.
    @Around("execution(* hello.hello_spring..*(..))")
    public Object timeTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
