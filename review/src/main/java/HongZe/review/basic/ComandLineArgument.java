package HongZe.review.basic;

public class ComandLineArgument {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (String string : args) {
			System.out.println(string);
			if ("-version".equals(string)) {
				System.out.println("1.0");
			}
		}
	}

}
