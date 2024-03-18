package HongZe.review.basic;

import java.util.Arrays;

public class ArraySort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
		Arrays.sort(ns);
		Arrays.stream(ns).forEach(System.out::println);
		System.out.println(Arrays.toString(ns));
	}

}
