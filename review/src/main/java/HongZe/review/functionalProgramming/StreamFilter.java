package HongZe.review.functionalProgramming;

import java.util.stream.Stream;

public class StreamFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream.generate(new NaturalSupplier()).filter(n -> n % 2 == 0).limit(10).forEach(System.out::println);
	}

}
