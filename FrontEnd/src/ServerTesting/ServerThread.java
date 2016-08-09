package ServerTesting;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServerThread extends Thread{
	private static final int PACKETSIZE = 1024;
	private static final int SLEEPINMS = 300;
	public static final int DEFAULTPORT = 5843;
	
	private int position = 0;
	
	protected DatagramSocket socket = null;
	protected Client client;
	
	
	
	
	
	/**
	 * This Method is called when the server want to send to the client
	 * @return
	 */
	private String infoToSend() {
		double voltage = Math.sin(position*2*Math.PI/30) * (8);
		voltage = Math.round(voltage * 10000d)/10000d;
		
		String string = Double.toString(voltage);
		position ++;
		return string;
	}

	
	
	
	
	
	
	
	
	
	/**
	 * Constructs a new IEP UDP server with the given name and port
	 * @param name The name of the Thread
	 * @param port The UDP Port Number
	 * @throws IOException A failed server construction 
	 */
	public ServerThread(String name, int port) throws IOException{
		super(name);
//		this.setDaemon(true);//stops this thread halting the JVM onClose()
        socket = new DatagramSocket(port);
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
	 * Default Port is {@value #DEFAULTPORT}
	 * @throws IOException
	 */
	public ServerThread() throws IOException{
		this("IEP Server on Port " + DEFAULTPORT, DEFAULTPORT);
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
		
		
		boolean run = true;
		while(run){
			String s = infoToSend();
			byte[] toSend = s.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(toSend, toSend.length, this.client.IP, this.client.SEND_PORT);
			
			
			try {
				this.print("Sending to: " + this.client.IP + ":" + this.client.SEND_PORT + "...");
				socket.send(sendPacket);
				this.print("sent");
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
