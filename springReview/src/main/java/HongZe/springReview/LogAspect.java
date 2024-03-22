package HongZe.springReview;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component
@Aspect
public class LogAspect {
	@Before("execution(public * HongZe.springReview.service.UserService.*(..))")
	public void doAccessCheck() {
		// TODO Auto-generated method stub
		System.err.println("[Before] do access check...");
	}

	@Around("execution(public * HongZe.springReview.service.MailService.*(..))")
	public Object doLog(ProceedingJoinPoint pjp) throws Throwable {
		// TODO Auto-generated method stub
		System.err.println("[Around] start " + pjp.getSignature());
		Object retVal = pjp.proceed();
		System.err.println("[Around] done " + pjp.getSignature());
		return retVal;
	}
}
