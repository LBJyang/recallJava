package HongZe.review.functionalProgramming;

import java.util.function.Supplier;

public class NaturalSupplier implements Supplier<Integer> {
	int n = 0;

	@Override
	public Integer get() {
		// TODO Auto-generated method stub
		n++;
		return n;
	}
}
