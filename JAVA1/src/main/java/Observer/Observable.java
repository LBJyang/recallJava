package Observer;

public interface Observable {
	void onPublished(Product product);

	void onPriceChange(Product product);
}
