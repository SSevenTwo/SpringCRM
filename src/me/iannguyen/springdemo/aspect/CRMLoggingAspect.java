package me.iannguyen.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// set up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// set up point cut declarations
	@Pointcut("execution(* me.iannguyen.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* me.iannguyen.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("execution(* me.iannguyen.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow() {
	};

	// make @before
	@Before("forAppFlow()")
	private void before(JoinPoint joinPoint) {

		// Display method sig
		String methodName = joinPoint.getSignature().toShortString();
		myLogger.info("====>>> Executing @Before on method:" + methodName);

		// Display method args
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			myLogger.info("====>>> Argument: " + arg);
		}
	}

	// make @afterreturning
	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	private void afterReturning(JoinPoint joinPoint, Object result) {

		// Display method sig
		String methodName = joinPoint.getSignature().toShortString();
		myLogger.info("====>>> Executing @AfterReturning on method:" + methodName);

		// Display results
		myLogger.info("====>>> Results: " + result);

	}

}
