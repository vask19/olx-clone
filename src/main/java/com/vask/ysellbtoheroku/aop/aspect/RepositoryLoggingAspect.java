package com.vask.ysellbtoheroku.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class RepositoryLoggingAspect {


    @Before("com.vask.ysellbtoheroku.aop.pointcut.RepositoryLoggingPointcut.allRepositoriesMethods()")
    public void beforeGetProductAdvice(JoinPoint joinPoint){
        MethodSignature methodSignatures = (MethodSignature) joinPoint.getSignature();
        Object[] methodArgs = joinPoint.getArgs();
        log.info("called method {} with args:{}",methodSignatures.getMethod(),methodArgs);

    }

    @AfterThrowing(throwing = "exception",
            pointcut = "com.vask.ysellbtoheroku.aop.pointcut.RepositoryLoggingPointcut.allRepositoriesMethods()")
    public void afterThrowingAllRepositoriesMethodLoggingAdvice(JoinPoint joinPoint,Throwable exception){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] methodArgs = joinPoint.getArgs();
        if (methodArgs.length == 0){
            log.error("method {} was completed with exception {}",methodSignature.getMethod(),exception);
        }
        else log.error("method {} with args: {} was completed with exception {}",methodSignature.getMethod(),methodArgs,exception);

    }



    @AfterReturning(
            pointcut = "com.vask.ysellbtoheroku.aop.pointcut.RepositoryLoggingPointcut.allRepositoriesMethods()")
    public void afterReturningAllRepositoriesMethodLoggingAdvice(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("method {} was completed successfully",methodSignature.getMethod());



    }
}
