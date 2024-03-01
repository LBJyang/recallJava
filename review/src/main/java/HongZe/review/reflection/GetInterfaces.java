package HongZe.review.reflection;

public class GetInterfaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<Integer> i = Integer.class;
		Class[] interfaces = i.getInterfaces();
		for (Class class1 : interfaces) {
			System.out.println(class1);
		}
	}

}
