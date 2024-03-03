package recallJava.web_mvc_pro.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.juli.logging.LogFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import recallJava.web_mvc_pro.controller.UserController;

@WebServlet(urlPatterns = "/")
public class DispatchServlet extends HttpServlet {
	private final Logger logger = (Logger) LogFactory.getLog(getClass());
	private Map<String, GetDispatcher> getMappings = new HashMap<>();
	private Map<String, PostDispatcher> postMappings = new HashMap<String, PostDispatcher>();
	private ViewEngine viewEngine;

	private List<Class<?>> controllers = List.of(UserController.class);

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		String path = req.getRequestURI().substring(req.getContextPath().length());
		GetDispatcher getDispatcher = getMappings.get(path);
		if (getDispatcher == null) {
			resp.sendError(404);
			return;
		}
		ModelAndView mv = null;
		try {
			mv = getDispatcher.invoke(req, resp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mv == null) {
			return;
		}
		if (mv.view.startsWith("redirect:")) {
			resp.sendRedirect(mv.view.substring(9));
			return;
		}
		PrintWriter pw = resp.getWriter();
		this.viewEngine.render(mv, pw);
		pw.flush();
	}
}
