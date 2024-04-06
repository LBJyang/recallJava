package HongZe.springMVC.web;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class GetBase {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		System.out.println(Base64.getEncoder().encodeToString("fan@123.com:123456".getBytes("UTF-8")));
	}

}
