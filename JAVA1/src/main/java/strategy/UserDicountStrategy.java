package strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserDicountStrategy implements DiscountStrategy {

	@Override
	public BigDecimal getDiscount(BigDecimal total) {
		// TODO Auto-generated method stub
		return total.multiply(new BigDecimal("0.1")).setScale(2, RoundingMode.DOWN);
	}

}
