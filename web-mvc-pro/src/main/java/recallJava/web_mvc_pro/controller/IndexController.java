package recallJava.web_mvc_pro.controller;

import jakarta.servlet.http.HttpSession;
import recallJava.web_mvc_pro.bean.User;
import recallJava.web_mvc_pro.framework.GetMapping;
import recallJava.web_mvc_pro.framework.ModelAndView;

public class IndexController {
	@GetMapping("/")
	public ModelAndView index(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return new ModelAndView("/index.html", "user", user);
	}

	@GetMapping("/hello")
	public ModelAndView hello(String name) {
		if (name == null) {
			name = "world";
		}
		return new ModelAndView("/hello.html", "name", name);
	}
}
