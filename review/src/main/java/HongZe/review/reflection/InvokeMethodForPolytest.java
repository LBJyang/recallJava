package HongZe.review.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import HongZe.review.beans.PersonForPolyTest;
import HongZe.review.beans.StudentForPolytest;

public class InvokeMethodForPolytest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Method method = PersonForPolyTest.class.getMethod("Hello");
		method.invoke(new StudentForPolytest());
	}

}
