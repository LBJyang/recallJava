package HongZe.review.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InvokeConstructor {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		Constructor<Integer> c = Integer.class.getConstructor(int.class);
		Integer i1 = (Integer) c.newInstance(123);
		Constructor<Integer> c1 = Integer.class.getConstructor(String.class);
		Integer i2 = (Integer) c1.newInstance("456");
		System.out.println(i1);
		System.out.println(i2);

	}

}
