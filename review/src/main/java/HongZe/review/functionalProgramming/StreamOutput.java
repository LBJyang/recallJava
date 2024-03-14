package HongZe.review.functionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOutput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<String> stream = Stream.of("Apple", "", null, "Pear", "  ", "Orange");
		List<String> list = stream.filter(s -> s != null && !s.isBlank()).collect(Collectors.toList());
		System.out.println("outputToList:" + list);
		outputToArray();
		outputToMap();
		outputByGroup();
	}

	private static void outputByGroup() {
		// TODO Auto-generated method stub
		List<String> list = List.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
		Map<String, List<String>> groupList = list.stream()
				.collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
		System.out.println("outputByGroups:" + groupList);
	}

	private static void outputToMap() {
		// TODO Auto-generated method stub
		Stream<String> stream = Stream.of("APPL:Apple", "MSFT:Microsoft");
		Map<String, String> map = stream
				.collect(Collectors.toMap(s -> s.substring(0, s.indexOf(':')), s -> s.substring(s.indexOf(':') + 1)));
		System.out.println("outputToMap:" + map);
	}

	private static void outputToArray() {
		// TODO Auto-generated method stub
		List<String> list = List.of("Apple", "Banana", "Orange");
		String[] arraysStrings = list.stream().toArray(String[]::new);
		System.out.println("outputToArray:" + Arrays.toString(arraysStrings));
	}

}
