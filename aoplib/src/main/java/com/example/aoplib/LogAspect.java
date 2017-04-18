package com.example.aoplib;

import android.content.Context;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();


        Object result = joinPoint.proceed();
        AOPLog.e("",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "();   end");

//        AOPLog.e("",  joinPoint.getTarget() + "\n" +
//                joinPoint.getSignature()+ "\n" +
//                joinPoint.getKind() + "\n" +
//                joinPoint.getArgs() + "\n" +
//                joinPoint.getSourceLocation() + "\n" +
//                joinPoint.getStaticPart() + "\n" +
//                joinPoint.getClass() + "\n" +
//                joinPoint.getThis()  + " \n"  );
        return result;
    }


}
