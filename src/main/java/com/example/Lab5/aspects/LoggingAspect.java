package com.example.Lab5.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.Lab5.services.*.*(..))")
    public void logBefore() {
        System.out.println("Executing method in the service layer...");
    }

    @AfterReturning(pointcut = "execution(* com.example.Lab5.services.*.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("Method executed successfully. Result: " + result);
    }
}