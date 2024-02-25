
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(10);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			es.submit(() -> {
				Thread.sleep(1000);
				return 0;
			});
		}
		es.close();
		long end = System.currentTimeMillis();
		System.out.printf("All virtual threads end at %s ms.\n", end - start);
	}
}
