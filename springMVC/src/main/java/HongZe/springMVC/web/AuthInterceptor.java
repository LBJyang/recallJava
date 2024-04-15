package HongZe.springMVC.web;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import HongZe.springMVC.entity.User;
import HongZe.springMVC.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
@Order(2)
public class AuthInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("pre authenticate {}...", request.getRequestURI());
		try {
			authenticateByHeader(request);
		} catch (RuntimeException e) {
			logger.warn("login by authorization header failed.", e);
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	private void authenticateByHeader(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Basic ")) {
			logger.info("try authenticate by authorization header");
			String up = new String(Base64.getDecoder().decode(authHeader.substring(6)), StandardCharsets.UTF_8);
			int pos = up.indexOf(":");
			if (pos > 0) {
				String emailString = up.substring(0, pos);
				String passwordString = up.substring(pos + 1);
				User user = userService.signin(emailString, passwordString);
				request.getSession().setAttribute(UserController.KEY_USER, user);
				logger.info("user {} login by authorization header ok.", emailString);
			}
		}
	}
}