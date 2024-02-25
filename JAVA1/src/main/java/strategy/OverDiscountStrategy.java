package strategy;

import java.math.BigDecimal;

public class OverDiscountStrategy implements DiscountStrategy {

	@Override
	public BigDecimal getDiscount(BigDecimal total) {
		// TODO Auto-generated method stub
		return total.compareTo(BigDecimal.valueOf(100)) > 0 ? BigDecimal.valueOf(20) : BigDecimal.ZERO;
	}

}
