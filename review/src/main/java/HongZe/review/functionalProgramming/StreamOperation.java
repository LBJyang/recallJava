package HongZe.review.functionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = List.of("Orange", "apple", "Banana").stream().sorted().collect(Collectors.toList());
		System.out.println(list);
		specialSort();
		eliminateDuplicates();
		truncate();
		concat();
		flatMap();
		parallelStream();
	}

	private static void parallelStream() {
		// TODO Auto-generated method stub
		List.of("Orange", "apple", "Banana").stream().parallel().sorted(String::compareToIgnoreCase)
				.forEach(System.out::println);
	}

	private static void flatMap() {
		// TODO Auto-generated method stub
		Stream<List<Integer>> s = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9));
		Stream<Integer> i = s.flatMap(list -> list.stream());
		i.forEach(System.out::println);
	}

	private static void concat() {
		// TODO Auto-generated method stub
		Stream<String> s1 = List.of("A", "B", "C").stream();
		Stream<String> s2 = List.of("D", "E").stream();
		Stream.concat(s1, s2).forEach(System.out::println);
	}

	private static void truncate() {
		// TODO Auto-generated method stub
		List.of("A", "B", "C", "D", "E", "F").stream().skip(2).limit(3).forEach(System.out::println);
	}

	private static void eliminateDuplicates() {
		// TODO Auto-generated method stub
		List.of("A", "B", "A", "C", "B", "D").stream().distinct().forEach(System.out::print);
	}

	private static void specialSort() {
		// TODO Auto-generated method stub
		List<String> list = List.of("Orange", "apple", "Banana").stream().sorted(String::compareToIgnoreCase)
				.collect(Collectors.toList());
		System.out.println(list);
	}

}
