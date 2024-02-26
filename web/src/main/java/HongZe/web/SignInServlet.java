package HongZe.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
	// 模拟一个数据库:
	private Map<String, String> users = Map.of("fan", "fan123", "zhuo", "zhuo123", "jiaze", "jiaze123", "jiahong",
			"jiahong123");

	// GET请求时显示登录页:
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.write("<h1>Sing In</h1>");
		pw.write("<form action=\"/signin\" method=\"post\">");
		pw.write("<p>Username:<input name=\"username\"></p>");
		pw.write("<p>Password:<input name=\"password\" type=\"password\"></p>");
		pw.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
		pw.write("</form>");
		pw.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nameString = req.getParameter("username");
		String passwordString = req.getParameter("password");
		String expectedPasswordString = users.get(nameString.toLowerCase());
		if (expectedPasswordString != null && expectedPasswordString.equals(passwordString)) {
			req.getSession().setAttribute("user", nameString);
			resp.sendRedirect("/");
		} else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
