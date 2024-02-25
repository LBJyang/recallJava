package LEARN.JAVA1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<List<Integer>> s = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9));
//		Stream<Integer> ss = s.flatMap(list -> list.stream());
		System.out.println(s.collect(Collectors.toList()));
//		System.out.println(ss.collect(Collectors.toList()));
	}
}

class NatualSupplier implements Supplier<Long> {
	long n;

	@Override
	public Long get() {
		// TODO Auto-generated method stub
		n++;
		return n;
	}

}
