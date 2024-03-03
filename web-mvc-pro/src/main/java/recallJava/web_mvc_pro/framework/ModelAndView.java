package recallJava.web_mvc_pro.framework;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	Map<String, Object> model;
	String view;

	public ModelAndView(String view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.model = Map.of();
	}

	public ModelAndView(String view, String name, Object value) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.model = new HashMap<String, Object>();
		this.model.put(name, value);
	}
}
