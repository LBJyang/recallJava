
public class Test {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new TestThread();
		t.start();
		Thread.sleep(10);
		t.interrupt();
//		t.join();
		System.out.println("end!");
	}
}

class TestThread extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int n = 0;
		while (!interrupted()) {
			n++;
			System.out.println("Say Hi!The" + n + "time!");
		}
	}
}
