package HongZe.review.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilterAndDecorator {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		try (CountInputStream input = new CountInputStream(
				new FileInputStream("C:\\Users\\Yang\\Desktop\\outputTest.txt"))) {
			int n = 0;
			while ((n = input.read()) != -1) {
				System.out.println((char) n);
			}
			System.out.println("Total read " + input.getBytesRead() + " bytes");
		}

	}

}

class CountInputStream extends FilterInputStream {
	private int count = 0;

	public int getBytesRead() {
		return count;
	}

	protected CountInputStream(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public int read() throws IOException {
		int n = in.read();
		if (n != -1) {
			count++;
		}
		return n;
	}

}