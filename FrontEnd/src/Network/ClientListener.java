package Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;

public class ClientListener extends NetworkComponent{
	
	public ClientListener(String serverAddress, int port) {
		super(serverAddress, port);
	}
	
	public ClientListener(int port) {
		super(port);
	}
	
	public ClientListener() {
		super();
	}
	
	@Override
	public void run() {
		super.run();
		
		byte[] receiveData = new byte[NetworkComponent.PACKETSIZE];
		DatagramPacket packet =  new DatagramPacket(receiveData, receiveData.length);
		
		boolean run = true;
		
		while(run){
			try {
				//System.out.println("Listening...");
				this.socket.receive(packet);
				//System.out.println("Found");
			} catch (IOException e) {
				run = false;
				e.printStackTrace();
			}
			
			this.buffer.add(new String(packet.getData()));
			
		}
	}
	
	public synchronized ArrayList<String> poll(){
		ArrayList<String> data = new ArrayList<String>();
		this.buffer.drainTo(data);
		
		return data;
	}

}
