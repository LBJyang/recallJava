package HongZe.springMVC.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import HongZe.springMVC.entity.User;
import HongZe.springMVC.service.UserService;
import HongZe.springMVC.web.UserController;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

//@Component
public class AuthFilter implements Filter {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		authentivateByHeader(req);
		chain.doFilter(request, response);
	}

	private void authentivateByHeader(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String authHeader = req.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Basic ")) {
			logger.info("try authenticate by authorization header");
			String up = new String(Base64.getDecoder().decode(authHeader.substring(6)), StandardCharsets.UTF_8);
			int pos = up.indexOf(":");
			if (pos > 0) {
				String emailString = up.substring(0, pos);
				String passwordString = up.substring(pos + 1);
				User user = userService.signin(emailString, passwordString);
				req.getSession().setAttribute(UserController.KEY_USER, user);
				logger.info("user {} login by authorization header ok.", emailString);
			}
		}

	}

}
