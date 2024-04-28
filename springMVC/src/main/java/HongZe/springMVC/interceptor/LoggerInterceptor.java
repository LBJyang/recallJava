package HongZe.springMVC.interceptor;

import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
@Order(1)
public class LoggerInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("preHandle {}...", request.getRequestURI());
		if (request.getParameter("debug") != null) {
			PrintWriter pw = response.getWriter();
			pw.write("<p>DEBUG MODE</p>");
			pw.flush();
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("postHandle {}.", request.getRequestURI());
		if (modelAndView != null) {
			modelAndView.addObject("__time__", LocalDateTime.now());
		}
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion {}: exception = {}", request.getRequestURI(), ex);
	}
}
