package HongZe.review.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import HongZe.review.beans.PersonForInvokeNoPublicMethod;

public class InvokeNoPublicMethod {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		PersonForInvokeNoPublicMethod p = new PersonForInvokeNoPublicMethod();
		Method m = p.getClass().getDeclaredMethod("setName", String.class);
		m.setAccessible(true);
		m.invoke(p, "jiaze");
		System.out.println(p.name);
	}

}
