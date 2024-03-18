package HongZe.review.basic;

import java.util.Arrays;

public class ArrayTraverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] i = { 1, 6, 5, 45, 23, 654, 188888888 };
		for (int j : i) {
			System.out.println(j);
		}
		useArraysToString(i);
	}

	private static void useArraysToString(int[] i) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(i));
	}

}
