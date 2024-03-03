package HongZe.web.MVCPro;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PostDispatcher extends HttpServlet {
	Object instance;
	Method method;
	Class<?>[] parameterClasses;
	ObjectMapper objectMapper;

	public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException, IOException {
		Object[] arguments = new Object[parameterClasses.length];
		for (int i = 0; i < parameterClasses.length; i++) {
			Class<?> parameterClass = parameterClasses[i];
			if (parameterClass == HttpServletRequest.class) {
				arguments[i] = request;
			} else if (parameterClass == HttpServletRequest.class) {
				arguments[i] = response;
			} else if (parameterClass == HttpSession.class) {
				arguments[i] = request.getSession();
			} else {
				BufferedReader reader = request.getReader();
				arguments[i] = this.objectMapper.readValue(reader, parameterClass);
			}
		}
		return (ModelAndView) this.method.invoke(this.instance, arguments);
	}
}
