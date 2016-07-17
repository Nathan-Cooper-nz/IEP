package ServerTesting;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServerThread extends Thread{
	private static final int PACKETSIZE = 1024;
	private static final int SLEEPINMS = 300;
	
	protected DatagramSocket socket = null;
	protected Client client;
	
	/**
	 * Constructs a new IEP UDP server with the given name and port
	 * @param name The name of the Thread
	 * @param port The UDP Port Number
	 * @throws IOException A failed server construction 
	 */
	public ServerThread(String name, int port) throws IOException{
		super(name);
        socket = new DatagramSocket(5843);
	}
	
	/**
	 * Constructs a new IEP UDP server with the given port
	 * Default name includes the port number
	 * @param port The UDP Port Number
	 * @throws IOException
	 */
	public ServerThread(int port) throws IOException{
		this("IEP Server on Port " + port, port);
	}
	
	/**
	 * Constructs a new IEP UDP server with the default name and port
	 * Default Port is 5843
	 * @throws IOException
	 */
	public ServerThread() throws IOException{
		this("IEP Server on Port 5843", 5843);
	}
	
	private void print(String message){
		System.out.println(this.getName() + ": " + message);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		this.print("started");
		while(!firstConnect());
		
		String s = "hey look some data";
		byte[] toSend = s.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(toSend, toSend.length, this.client.IP, this.client.PORT);
		
		boolean run = true;
		while(run){
			try {
				socket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				run = false;
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(this.SLEEPINMS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Checks if a connection has been established
	 * @return
	 */
	private boolean firstConnect(){
		
		try {
			DatagramPacket p = this.receive();
			
			this.client = new Client(p);
			
			socket.send(p);
			this.print("Connected to " + this.client.toString());
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Blocking method to receive a UDP packet
	 * @return DatagramPacket the found packet
	 * @throws IOException  If an I/O error occurs
	 */
	private DatagramPacket receive() throws IOException{
		byte[] reciveData = new byte[PACKETSIZE];
		
		DatagramPacket packet = new DatagramPacket(reciveData, reciveData.length);
		socket.receive(packet);
		
		
		return packet;
	}
}
