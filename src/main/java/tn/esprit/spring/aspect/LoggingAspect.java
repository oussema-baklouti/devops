package tn.esprit.spring.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import tn.esprit.spring.services.MissionServiceImpl;

@Aspect
public class LoggingAspect {
	private static final Logger l = LogManager.getLogger(MissionServiceImpl.class);

	@Around ("execution(* tn.esprit.spring.service.*.*(..))")
	public Object executionNotification(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj =pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		if (elapsedTime >0.003) {
			l.info("la ethode a depassé bla bla bla " );
			
		} else l.info("all good ");
		return obj;
		
	}
	

}
