package HongZe.review.IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Serialization {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
			output.writeInt(123);
			output.writeUTF("hello");
			output.writeObject(Integer.valueOf(456));
		}
		System.out.println(Arrays.toString(buffer.toByteArray()));
	}

}
