package HongZe.review.encryption;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashReview {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update("hello".getBytes(StandardCharsets.UTF_8));
		md.update("world".getBytes(StandardCharsets.UTF_8));
		byte[] result = md.digest();
		System.out.println(new BigInteger(1, result).toString(16));
		System.out.println(Integer.toHexString("helloworld".hashCode()));
	}

}
