package strategy;

import java.math.BigDecimal;

public class DiscountContent {
	private DiscountStrategy ds = new UserDicountStrategy();

	public void setDiscountStrategy(DiscountStrategy ds) {
		this.ds = ds;
	}

	public BigDecimal calculatePrice(BigDecimal total) {
		return total.subtract(this.ds.getDiscount(total).setScale(2));
	}
}
