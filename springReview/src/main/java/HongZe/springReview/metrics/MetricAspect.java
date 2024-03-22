package HongZe.springReview.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MetricAspect {
	@Around("@annotation(metricTime)")
	public Object metric(ProceedingJoinPoint joinPoint, MetricTime metricTime) throws Throwable {
		String nameString = metricTime.value();
		long start = System.currentTimeMillis();
		try {
			return joinPoint.proceed();
		} finally {
			// TODO: handle finally clause
			long t = System.currentTimeMillis() - start;
			System.out.println("[Metrics] " + nameString + ": " + t + "ms");
		}
	}
}
