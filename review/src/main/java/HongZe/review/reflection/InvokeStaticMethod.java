package HongZe.review.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeStaticMethod {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Method method = Integer.class.getMethod("parseInt", String.class);
		Integer i = (Integer) method.invoke(null, "12345");
		System.out.println(i);
	}

}
