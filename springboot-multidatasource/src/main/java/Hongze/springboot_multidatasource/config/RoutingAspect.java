package Hongze.springboot_multidatasource.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RoutingAspect {
	@Around("@annotation(routingWithSlave)")
	public Object routingWithDataSource(ProceedingJoinPoint joinPoint, RoutingWithSlave routingWithSlave)
			throws Throwable {
		try (RoutingDataSourceContext rtx = new RoutingDataSourceContext(RoutingDataSourceContext.SLAVE_DATASOURCE)) {
			return joinPoint.proceed();
		}
	}
}
