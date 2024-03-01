package HongZe.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/user")
public class userServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		School school = new School("BUPT", "West Soil City Road 10");
		User user = new User(1, "yang&&li", school);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
	}
}
