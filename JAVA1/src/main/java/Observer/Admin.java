package Observer;

public class Admin implements Observable {

	@Override
	public void onPriceChange(Product product) {
		// TODO Auto-generated method stub
		System.out.println("[Admin] on product price changed: " + product);
	}

	public void onPublished(Product product) {
		System.out.println("[Admin] on product published: " + product);
	}
}
