package HongZe.review.OOP;

import java.math.BigDecimal;

public class BigDecimalRecall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal bd1 = new BigDecimal("12.75");
		BigDecimal bd2 = new BigDecimal("0.15");
		BigDecimal[] bds = bd1.divideAndRemainder(bd2);
		if (bds[1].signum() == 0) {
			
		}
	}

}
