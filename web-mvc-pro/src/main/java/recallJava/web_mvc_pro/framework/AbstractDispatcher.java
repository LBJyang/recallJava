package recallJava.web_mvc_pro.framework;

import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractDispatcher {
	public abstract ModelAndView invoke(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException;
}
