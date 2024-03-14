package HongZe.review.functionalProgramming;

import java.util.List;

public class StreamMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List.of("  Apple ", " pear ", " ORANGE", " BaNaNa ").stream().map(String::trim).map(String::toLowerCase)
				.forEach(System.out::println);
	}

}
