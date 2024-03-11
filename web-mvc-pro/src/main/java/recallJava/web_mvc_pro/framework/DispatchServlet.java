package recallJava.web_mvc_pro.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import recallJava.web_mvc_pro.controller.IndexController;
import recallJava.web_mvc_pro.controller.UserController;

@WebServlet(urlPatterns = "/")
public class DispatchServlet extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String, GetDispatcher> getMappings = new HashMap<>();
	private Map<String, PostDispatcher> postMappings = new HashMap<String, PostDispatcher>();
	private ViewEngine viewEngine;

	private List<Class<?>> controllers = List.of(UserController.class, IndexController.class);

	private static final Set<Class<?>> supportedGetParameterTypes = Set.of(int.class, boolean.class, String.class,
			long.class, HttpServletRequest.class, HttpServletResponse.class, HttpSession.class);
	private static final Set<Class<?>> supportedPostParameterTypes = Set.of(HttpServletRequest.class,
			HttpServletResponse.class, HttpSession.class);

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		logger.info("init{}...", getClass().getSimpleName());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		for (Class<?> controllerClass : controllers) {
			try {
				Object controllerInstance = controllerClass.getConstructor().newInstance();
				for (Method method : controllerClass.getMethods()) {
					if (method.getAnnotation(GetMapping.class) != null) {
						if (method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class) {
							throw new UnsupportedOperationException(
									"Unsupported return type: " + method.getReturnType() + " for method: " + method);
						}
						for (Class<?> parameterClass : method.getParameterTypes()) {
							if (!supportedGetParameterTypes.contains(parameterClass)) {
								throw new UnsupportedOperationException(
										"Unsupported parameter type:" + parameterClass + "for method" + method);
							}
						}
						String[] parameterNames = Arrays.stream(method.getParameters()).map(p -> p.getName())
								.toArray(String[]::new);
						String path = method.getAnnotation(GetMapping.class).value();
						logger.info("FOUND GET:{}=>{}", path, method);
						this.getMappings.put(path, new GetDispatcher(controllerInstance, method, parameterNames,
								method.getParameterTypes()));
					} else if (method.getAnnotation(PostMapping.class) != null) {
						if (method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class) {
							throw new UnsupportedOperationException(
									"Unsupported return type: " + method.getReturnType() + " for method: " + method);
						}
						Class<?> requestBodyClass = null;
						for (Class<?> parameterClass : method.getParameterTypes()) {
							if (!supportedPostParameterTypes.contains(parameterClass)) {
								if (requestBodyClass == null) {
									requestBodyClass = parameterClass;
								} else {
									throw new UnsupportedOperationException("Unsupported duplicate request body type: "
											+ parameterClass + " for method: " + method);
								}
							}
						}
						String path = method.getAnnotation(PostMapping.class).value();
						logger.info("Found POST: {} => {}", path, method);
						this.postMappings.put(path, new PostDispatcher(controllerInstance, method,
								method.getParameterTypes(), objectMapper));
					}
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.viewEngine = new ViewEngine(getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.process(req, resp, getMappings);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.process(req, resp, this.postMappings);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp,
			Map<String, ? extends AbstractDispatcher> dispatchMap) throws IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		String path = req.getRequestURI().substring(req.getContextPath().length());
		AbstractDispatcher dispatcher = dispatchMap.get(path);
		if (dispatcher == null) {
			resp.sendError(404);
			return;
		}
		ModelAndView mv = null;
		try {
			mv = dispatcher.invoke(req, resp);
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
