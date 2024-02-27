package HongZe.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("user");
		String lang = parseLanguageFromCookies(req);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("X-power-By", "JavaEE Servlet");
		PrintWriter pw = resp.getWriter();
		if (lang.equals("en")) {
			pw.write("<h1>Welcome, " + (user != null ? user : "Guest") + "!</h1>");
			if (user == null) {
				pw.write("<p><a href=\"/signin\">Sign In</a></p>");
			} else {
				pw.write("<p><a href=\"/signout\">Sign Out</a></p>");
			}
		} else {
			pw.write("<h1>欢迎, " + (user != null ? user : "客人") + "!</h1>");
			if (user == null) {
				pw.write("<p><a href=\"/signin\">登录</a></p>");
			} else {
				pw.write("<p><a href=\"/signout\">退出</a></p>");
			}
		}
		pw.write("<p><a href=\"/pref?lang=en\">English</a> | <a href=\"/pref?lang=zh\">中文</a>");
		pw.flush();
	}

	private String parseLanguageFromCookies(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lang")) {
					return cookie.getValue();
				}
			}
		}
		return "en";
	}
}
