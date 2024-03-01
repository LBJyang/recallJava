package HongZe.review.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeMethod {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Class<String> c = String.class;
		Method m = c.getMethod("substring", int.class);
		String s = (String) m.invoke("Hello World!", 6);
		System.out.println(s);
	}

}
