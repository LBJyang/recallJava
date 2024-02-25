import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 6666);
		try (InputStream inputStream = socket.getInputStream()) {
			try (OutputStream outputStream = socket.getOutputStream()) {
				handle(inputStream, outputStream);
			}
		}
		socket.close();
		System.out.println("Client Disconnected!");
	}

	public static void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
		var writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
		var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		System.out.println("[server]:" + reader.readLine());
		Scanner scanner = new Scanner(System.in);
		for (;;) {
			System.out.println(">>>");
			String string = scanner.nextLine();
			writer.write(string);
			writer.newLine();
			writer.flush();
			String resp = reader.readLine();
			System.out.println(">>>" + resp);
			if (resp.equals("bye")) {
				break;
			}
		}
	}
}
