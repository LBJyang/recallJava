package HongZe.review;

import java.math.BigInteger;

public class BigIntegerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger n = new BigInteger("999999").pow(99);
		float f = n.floatValue();
		System.out.println(f);
	}

}
