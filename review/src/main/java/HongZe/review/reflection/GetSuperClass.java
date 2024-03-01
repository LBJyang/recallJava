package HongZe.review.reflection;

public class GetSuperClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<Integer> i = Integer.class;
		Class<?> n = i.getSuperclass();
		System.out.println(n);
		Class<?> o = n.getSuperclass();
		System.out.println(o);
		System.out.println(o.getSuperclass());
	}

}
