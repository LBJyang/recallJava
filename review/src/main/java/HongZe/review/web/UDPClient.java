package HongZe.review.web;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket ds = new DatagramSocket();
		ds.setSoTimeout(1000);
		ds.connect(InetAddress.getByName("localhost"), 6666);

		byte[] data = new byte[1000];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		ds.send(packet);

		byte[] buffer = new byte[1024];
		packet = new DatagramPacket(buffer, buffer.length);
		ds.receive(packet);
		String resp = new String(buffer, packet.getOffset(), packet.getLength());
		ds.disconnect();

		ds.close();
	}

}
