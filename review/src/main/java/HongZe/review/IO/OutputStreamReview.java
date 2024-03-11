package HongZe.review.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OutputStreamReview {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\Yang\\Desktop\\outputTest.txt");
		f.createNewFile();
		try (OutputStream output = new FileOutputStream(f)) {
			output.write(72);
			output.write(101); // e
			output.write(108); // l
			output.write(108); // l
			output.write(111); // o
		}

		writeBytes();
	}

	private static void writeBytes() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		try (OutputStream output = new FileOutputStream("C:\\Users\\Yang\\Desktop\\outputTest.txt", true)) {
			output.write(" world".getBytes(StandardCharsets.UTF_8));
		}
	}
}
