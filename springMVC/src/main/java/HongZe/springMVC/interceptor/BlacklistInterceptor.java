package HongZe.springMVC.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import HongZe.springMVC.mbean.BlacklistMBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Order(1)
@Component
public class BlacklistInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	BlacklistMBean blacklistMBean;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String ip = request.getRemoteAddr();
		logger.info("check ip address {} ...", ip);
		if (blacklistMBean.shouldBlock(ip)) {
			logger.info("will block ip {} for it is in blacklist.", ip);
			response.sendError(403);
			return false;
		}
		return true;
	}
}
