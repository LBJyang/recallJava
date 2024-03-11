package HongZe.review;

public class StringReview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		equalTest();
		stringContainTest();
	}

	private static void equalTest() {
		// TODO Auto-generated method stub
		String s1 = "hello";
		String s2 = "Hello".toLowerCase();
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
	}

	private static void stringContainTest() {
		// TODO Auto-generated method stub
		System.out.println("hello".contains("ll"));
	}
}
