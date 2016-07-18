package Network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class NetworkComponent extends Thread{
	public static final int DEFAULTPORT = 5843;
	public static final int PACKETSIZE = 1024;
	
	DatagramSocket socket;
	InetAddress IP;
	int port;
	
	ArrayBlockingQueue<String> buffer;
	
	public NetworkComponent(String serverAddress, int port) {
		this.setDaemon(true);//stops the Thread Halting the JVM onClose()
		
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			this.IP = InetAddress.getByName(serverAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.port = port;
		
		this.buffer = new ArrayBlockingQueue<>(100);
	}
	
	public NetworkComponent(int port){
		this("localhost", port);
	}
	
	public NetworkComponent() {
		this("localhost", DEFAULTPORT);
	}

}
