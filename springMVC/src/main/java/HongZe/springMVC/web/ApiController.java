package HongZe.springMVC.web;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import HongZe.springMVC.entity.User;
import HongZe.springMVC.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public Callable<List<User>> users() {
		logger.info("get users...");
		return () -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("return users...");
			return userService.getUsers();
		};
	}

	@GetMapping("/version")
	public Map<String, String> version() {
		logger.info("get version...");
		return Map.of("version", "1.0");
	}

	@GetMapping("/users/{id}")
	public DeferredResult<User> user(@PathVariable("id") long id) {
		DeferredResult<User> deferredResult = new DeferredResult<User>(3000L);
		new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				User user = userService.getUserById(id);
				deferredResult.setResult(user);
				logger.info("deferred result is set!");
			} catch (Exception e) {
				e.printStackTrace();
				deferredResult.setErrorResult(Map.of("error", e.getClass().getSimpleName(), "message", e.getMessage()));
				logger.info("error deferred result is set!");
			}
		}).start();
		return deferredResult;
	}

	@PostMapping("/signin")
	public Map<String, Object> signin(@RequestBody SigninRequest signinRequest) {
		User user = userService.signin(signinRequest.email, signinRequest.password);
		return Map.of("user", user);
	}

	public static class SigninRequest {
		public String email;
		public String password;
	}
}
