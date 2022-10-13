package com.vask.ysellbtoheroku.aop.pointcut;
import org.aspectj.lang.annotation.Pointcut;

public class RepositoryLoggingPointcut {

    @Pointcut("execution(* com.vask.ysellbtoheroku.repository.*.*(..))")
    public void allRepositoriesMethods(){
    }


}
