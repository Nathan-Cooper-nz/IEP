package Network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class NetworkComponent extends Thread{
	
	public static final int PACKETSIZE = 1024;
	
	protected DatagramSocket socket;
	protected InetAddress IP;
	protected int port;
	
	protected ArrayBlockingQueue<String> buffer;
	
	/**
	 * Creates a new NetworkComponent with the given Address and Port
	 * @param serverAddress The Servers InetAddress
	 * @param port The Server Port
	 */
	public NetworkComponent(String serverAddress, int port) {
		this.setDaemon(true);//stops the Thread Halting the JVM onClose()
		
		try {
			this.socket = new DatagramSocket(port, IP);
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
	
	/**
	 * Creates a new NetworkComponent with the given Port on 127.0.0.1
	 * @param port The Server Port
	 */
	public NetworkComponent(int port){
		this("localhost", port);
	}
	
	/**
	 * Creates a new NetworkComponent with the default Port on 127.0.0.1
	 * The default port is {@value #DEFAULTPORT}
	 */
	public NetworkComponent() {
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the current socket
	 * Attempts to halt the Thread
	 * @throws InterruptedException
	 */
	public void close() throws InterruptedException{
		this.socket.close();
		this.sleep(Long.MAX_VALUE);
		
	}
	
	public int getPort(){
		return this.socket.getLocalPort();
	}

}
