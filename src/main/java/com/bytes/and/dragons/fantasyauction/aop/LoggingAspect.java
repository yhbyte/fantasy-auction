package com.bytes.and.dragons.fantasyauction.aop;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(InvocationLog)")
    public Object logInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        InvocationLog annotation = method.getAnnotation(InvocationLog.class);

        log.info("Invoking method: {} with arguments: {}", method.getName(), joinPoint.getArgs());
        Object result = joinPoint.proceed();
        
        if (annotation.logAfter()) {
            log.info("Exiting method: {} with result: {}", method.getName(), result);
        }

        return result;
    }
}
