package HongZe.review.IO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FilesReview {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[] data = Files.readAllBytes(Path.of("C:\\Users\\Yang\\Desktop\\outputTest.txt"));
		System.out.println(Arrays.toString(data));
		Files.writeString(Path.of("C:\\Users\\Yang\\Desktop\\outputTest.txt"), "What the hell", StandardCharsets.UTF_8);
	}

}
