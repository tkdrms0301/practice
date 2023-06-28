package com.kit.dormitory.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElpasedTimeMeasureAop {
    //@Around("execution(* com.kit.dormitory..*(..))")
    @Around("@annotation(com.kit.dormitory.annotation.ElpasedTimeLog)")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Object result = joinPoint.proceed();
            return result;
        }finally {
            stopWatch.stop();
            System.out.println("stopWatch.getTotalTimeMillis() = " + stopWatch.getTotalTimeMillis());
        }
    }
}
