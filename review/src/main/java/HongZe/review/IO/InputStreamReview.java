package HongZe.review.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class InputStreamReview {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		try (InputStream input = new FileInputStream("C:\\Users\\Yang\\Desktop\\新建 文本文档.txt")) {
			byte[] buffer = new byte[100];
			int n = 0;
			while ((n = input.read(buffer)) != -1) {
				System.out.println("read" + n + "bytes");
			}
		}

	}

}
