package HongZe.springMVC.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import HongZe.springMVC.entity.User;
import HongZe.springMVC.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	public static final String KEY_USER = "__user__";

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserService userService;

	@GetMapping("/")
	public ModelAndView index(HttpSession httpSession) {
		User user = (User) httpSession.getAttribute(KEY_USER);
		Map<String, User> model = new HashMap<String, User>();
		if (user != null) {
			model.put(user.getName(), user);
		}
		return new ModelAndView("index.html", model);
	}

	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("register.html");
	}

	@PostMapping("/register")
	public ModelAndView doRegister(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("name") String name) {
		User user = userService.register(email, password, name);
		logger.info("user registered: {}", user.getEmail());
		return new ModelAndView("redirect:/signin");
	}

	@GetMapping("/signin")
	public ModelAndView signin(HttpSession httpSession) {
		User user = (User) httpSession.getAttribute(KEY_USER);
		if (user != null) {
			return new ModelAndView("redirect:/profile");
		}
		return new ModelAndView("signin.html");
	}

	@PostMapping("/signin")
	public ModelAndView doSignin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession httpSession) {
		User user = userService.signin(email, password);
		httpSession.setAttribute(KEY_USER, user);
		return new ModelAndView("redirect:/profile");
	}

//	@GetMapping("/profile")
//	public ModelAndView profile(HttpSession session) {
//		User user = (User) session.getAttribute(KEY_USER);
//		if (user == null) {
//			return new ModelAndView("redirect:/signin");
//		}
//		return new ModelAndView("profile.html	", Map.of("user", user));
//	}

	@GetMapping("/profile")
	public ModelAndView profile(HttpSession session) {
		User user = (User) session.getAttribute(KEY_USER);
		if (user == null) {
			return new ModelAndView("redirect:/signin");
		}
		return new ModelAndView("profile.html", Map.of("user", user));
	}

	@GetMapping("/signout")
	public String signout(HttpSession httpSession) {
		httpSession.removeAttribute(KEY_USER);
		return "redirect:/signin";
	}
}
