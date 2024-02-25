package strategy;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiscountContent dc = new DiscountContent();

		BigDecimal price1 = dc.calculatePrice(BigDecimal.valueOf(105));
		System.out.println(price1);

		dc.setDiscountStrategy(new OverDiscountStrategy());
		BigDecimal price2 = dc.calculatePrice(BigDecimal.valueOf(105));
		System.out.println(price2);

		dc.setDiscountStrategy(new PrimeDiscountStrategy());
		BigDecimal price3 = dc.calculatePrice(BigDecimal.valueOf(105));
		System.out.println(price3);
	}
}
