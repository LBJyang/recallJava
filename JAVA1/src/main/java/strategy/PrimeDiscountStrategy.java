package strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrimeDiscountStrategy implements DiscountStrategy {

	@Override
	public BigDecimal getDiscount(BigDecimal total) {
		// TODO Auto-generated method stub
		BigDecimal OverDicount = total.compareTo(BigDecimal.valueOf(100)) > 0 ? BigDecimal.valueOf(20)
				: BigDecimal.ZERO;
		BigDecimal primDiscount = total.subtract(OverDicount).multiply(new BigDecimal("0.3")).setScale(2,
				RoundingMode.DOWN);
		return OverDicount.add(primDiscount);
	}

}
