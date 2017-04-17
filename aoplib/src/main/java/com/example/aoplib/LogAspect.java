package com.example.aoplib;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by little on 2017/4/17 0017.
 */


@Aspect
public class LogAspect {

    @Pointcut("execution(@com.example.aoplib.MyLog * *(..))")
    public void logMethod (){}

    @Around("logMethod()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        AOPLog.e("",className + "." + methodName + "();   start");
        Object result = joinPoint.proceed();

        return result;
    }

}
