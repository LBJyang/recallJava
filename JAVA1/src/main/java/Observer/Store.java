package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
	private List<Observable> observers = new ArrayList<Observable>();
	private Map<String, Product> products = new HashMap<>();

	public void addObserver(Observable observer) {
		this.observers.add(observer);
	}

	public void addProduct(String name, double price) {
		// TODO Auto-generated method stub
		Product product = new Product(name, price);
		products.put(name, product);
		observers.forEach(o -> o.onPublished(product));
	}

	public void setProductPrice(String name, double price) {
		// TODO Auto-generated method stub
		Product product = products.get(name);
		product.setPrice(price);
		observers.forEach(o -> o.onPriceChange(product));
	}

}
