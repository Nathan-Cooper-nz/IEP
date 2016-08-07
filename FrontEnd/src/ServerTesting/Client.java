package ServerTesting;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Client {
	public final InetAddress IP;
	public final int PORT;
	public final int SEND_PORT;
	
	public Client(DatagramPacket p) {
		this.IP = p.getAddress();
		this.PORT = p.getPort();
		this.SEND_PORT = Integer.parseInt(new String(p.getData()).trim());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return IP + ":" + PORT + " listening on - " + this.SEND_PORT;
	}
}
