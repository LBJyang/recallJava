package HongZe.review.functionalProgramming;

import java.util.stream.LongStream;

public class FibonacciPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongStream fibonaciciLongStream = LongStream.generate(new FibonacciGeneratorSupplier());
		fibonaciciLongStream.limit(10).forEach(System.out::println);
	}

}
