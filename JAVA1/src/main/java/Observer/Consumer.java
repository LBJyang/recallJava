package Observer;

public class Consumer implements Observable {

	@Override
	public void onPublished(Product product) {
		// TODO Auto-generated method stub
		System.out.println("[Consumer] on product price changed: " + product);
	}

	@Override
	public void onPriceChange(Product product) {
		// TODO Auto-generated method stub
		System.out.println("[Consumer] on product published: " + product);
	}

}
