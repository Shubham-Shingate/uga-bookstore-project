package com.uga.email_service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.uga.email_service.controller.*.*(..))")
	private void pointcutforController() {
	}

	@Before("pointcutforController()")
	public void before(JoinPoint joinPoint) {
		log.info("Before method execution {}", joinPoint.getSignature().getName());
	}

	@AfterReturning(value = "pointcutforController()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		log.info("{} returned with value {}", joinPoint, result);
	}

	@AfterThrowing(pointcut = "pointcutforController()", throwing = "e")
	public void logAfterThrowingException(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
	}
	@After(value = "pointcutforController()")
	public void after(JoinPoint joinPoint) {
		log.info("after execution of {}", joinPoint);
	}
	@Around("pointcutforController()")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
   
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
          
        final StopWatch stopWatch = new StopWatch();
          
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
  
        log.info("Execution time of " + className + "." + methodName + " "
                            + ":: " + stopWatch.getTotalTimeMillis() + " ms");
  
        return result;
    }

}
