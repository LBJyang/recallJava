package HongZe.review.basic;

import java.util.Scanner;

public class InputAndOutput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int d = 12900000;
		System.out.printf("int = %d ,hex=%08x\n", d, d);

		input();
	}

	private static void input() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input your name:");
		String nameString = scanner.nextLine();
		System.out.println("Input your socre:");
		int score = scanner.nextInt();
		System.out.printf("Hi,%s,your score is %d\n", nameString, score);
	}

}
