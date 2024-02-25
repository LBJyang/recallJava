package Observer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Admin a = new Admin();
		Consumer c = new Consumer();

		Store s = new Store();

		s.addObserver(a);
		s.addObserver(c);
		s.addObserver(new Observable() {

			@Override
			public void onPublished(Product product) {
				// TODO Auto-generated method stub
				System.out.println("[Log] on product published: " + product);
			}

			@Override
			public void onPriceChange(Product product) {
				// TODO Auto-generated method stub
				System.out.println("[Log] on product price changed: " + product);
			}
		});

		s.addProduct("design pattern", 36.25);
		s.addProduct("thinking in java", 105.68);
		s.setProductPrice("design pattern", 31.65);
	}

}
