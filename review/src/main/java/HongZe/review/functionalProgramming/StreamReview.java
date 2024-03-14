package HongZe.review.functionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamReview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<String> stream = Stream.of("A", "B", "C", "D");
		stream.forEach(System.out::println);
		getStreamByCollection();
		getStreamBySupplier();
	}

	private static void getStreamBySupplier() {
		// TODO Auto-generated method stub
		Stream<Integer> naturalStream = Stream.generate(new NaturalSupplier());
		naturalStream.limit(10).forEach(System.out::println);
	}

	private static void getStreamByCollection() {
		// TODO Auto-generated method stub
		Stream<String> stream1 = Arrays.stream(new String[] { "E", "F" });
		Stream<String> stream2 = List.of("G", "H", "I").stream();
		stream1.forEach(System.out::println);
		stream2.forEach(System.out::println);
	}

}
