package HongZe.review.reflection;

public class GetSuperClassInterfaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<?> is = Integer.class.getSuperclass();
		System.out.println(is);
		Class[] isinterfaces = is.getInterfaces();
		for (Class class1 : isinterfaces) {
			System.out.println(class1);
		}
	}

}
