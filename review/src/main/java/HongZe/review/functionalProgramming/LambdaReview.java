package HongZe.review.functionalProgramming;

import java.util.Arrays;

public class LambdaReview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arraysStrings = new String[] { "Apple", "Orange", "nanana", "lemon" };
		Arrays.sort(arraysStrings, String::compareToIgnoreCase);
		System.out.println(String.join(",", arraysStrings));
	}

}
