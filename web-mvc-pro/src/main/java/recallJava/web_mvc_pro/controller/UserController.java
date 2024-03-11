package recallJava.web_mvc_pro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import recallJava.web_mvc_pro.bean.SigninBean;
import recallJava.web_mvc_pro.bean.User;
import recallJava.web_mvc_pro.framework.GetMapping;
import recallJava.web_mvc_pro.framework.ModelAndView;
import recallJava.web_mvc_pro.framework.PostMapping;

public class UserController {

	private Map<String, User> userDatabase = new HashMap<String, User>() {
		{
			List<User> users = List.of(new User("yangjiaze", "elder son", "yangjiaze@dmail.com", "jiaze123"),
					new User("yangjiahong", "younger son", "yangjiahong@dmail.com", "jiahong123"));
			users.forEach(user -> {
				put(user.email, user);
			});
		}
	};

	@GetMapping("/signin")
	public ModelAndView signin() {
		return new ModelAndView("/signin.html");
	}

	@GetMapping("/signout")
	public ModelAndView signout(HttpSession session) {
		session.removeAttribute("user");
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/signin")
	public ModelAndView signin(SigninBean bean, HttpServletResponse response, HttpSession session) throws IOException {
		User user = userDatabase.get(bean.email);
		if (user == null || !user.password.equals(bean.password)) {
			response.setContentType("application/jason");
			PrintWriter pw = response.getWriter();
			pw.write("{\"error\":\"Bad email or password\"}");
			pw.flush();
		} else {
			session.setAttribute("user", user);
			response.setContentType("application/jason");
			PrintWriter pw = response.getWriter();
			pw.write("{\"result\":\"true\"}");
			pw.flush();
		}
		return null;
	}

	@GetMapping("/user/profile")
	public ModelAndView profile(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:/signin");
		} else {
			return new ModelAndView("/profile.html", "user", user);
		}
	}
}
