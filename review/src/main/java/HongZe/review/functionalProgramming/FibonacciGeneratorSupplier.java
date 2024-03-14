package HongZe.review.functionalProgramming;

import java.util.function.LongSupplier;

public class FibonacciGeneratorSupplier implements LongSupplier {
	int front = 1;
	int back = 0;
	int fibNum = 0;

	@Override
	public long getAsLong() {
		// TODO Auto-generated method stub
		fibNum = front + back;
		front = back;
		back = fibNum;
		return fibNum;
	}

}
