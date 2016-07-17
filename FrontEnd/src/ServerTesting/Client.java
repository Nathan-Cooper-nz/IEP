package ServerTesting;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Client {
	public final InetAddress IP;
	public final int PORT;
	
	public Client(DatagramPacket p) {
		this.IP = p.getAddress();
		this.PORT = p.getPort();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return IP + ":" + PORT;
	}
}
