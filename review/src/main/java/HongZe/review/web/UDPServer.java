package HongZe.review.web;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UDPServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket ds = new DatagramSocket(6666);
		for (;;) {
			byte[] buffer = new byte[1000];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			ds.receive(packet);
			String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
			byte[] data = "Got the message".getBytes("UTF-8");
			packet.setData(data);
			ds.send(packet);
		}

	}

}
