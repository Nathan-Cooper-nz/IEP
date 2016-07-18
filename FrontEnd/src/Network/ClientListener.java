package Network;

import java.io.IOException;
import java.net.DatagramPacket;

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
				this.socket.receive(packet);
			} catch (IOException e) {
				run = false;
				e.printStackTrace();
			}
			
			this.buffer.add(new String(packet.getData()));
			
		}
	}
	
	public synchronized String poll(){
		return this.buffer.poll();
	}

}
