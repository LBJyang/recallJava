package HongZe.review.thread;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new MyRunnable());
		thread.start();
	}

}
