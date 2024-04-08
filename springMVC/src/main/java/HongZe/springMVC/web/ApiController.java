package HongZe.springMVC.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HongZe.springMVC.entity.User;
import HongZe.springMVC.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	UserService userService;

	@GetMapping("/users")
	List<User> users() {
		return userService.getUsers();
	}

	@GetMapping("/users/{id}")
	User user(@PathVariable("id") long id) {
		return userService.getUserById(id);
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
