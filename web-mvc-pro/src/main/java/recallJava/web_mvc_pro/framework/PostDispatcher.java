package recallJava.web_mvc_pro.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PostDispatcher extends AbstractDispatcher {
	Object instance;
	Method method;
	Class<?>[] parameterClasses;
	ObjectMapper objectMapper;

	public PostDispatcher(Object instance, Method method, Class<?>[] parameterClasses, ObjectMapper objectMapper) {
		super();
		this.instance = instance;
		this.method = method;
		this.parameterClasses = parameterClasses;
		this.objectMapper = objectMapper;
	}

	public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException {
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
				BufferedReader reader;
				try {
					reader = request.getReader();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return (ModelAndView) this.method.invoke(this.instance, arguments);
	}
}
