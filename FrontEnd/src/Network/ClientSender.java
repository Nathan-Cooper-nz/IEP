package Network;

import java.io.IOException;
import java.net.DatagramPacket;

public class ClientSender extends NetworkComponent{
	
	public ClientSender(String serverAddress, int port) {
		super(serverAddress, port);
	}
	
	public ClientSender(int port) {
		super(port);
	}
	
	public ClientSender() {
		super();
	}
	
	
	@Override
	public void run() {
		super.run();
		
		boolean run = true;
		while(run){
			if(this.buffer.isEmpty()){
				try {
					Thread.sleep(10); //give the system a bit of a break
				} catch (InterruptedException e) {
					run = false;
					e.printStackTrace();
				}
			}else{
				String nextToSend = this.buffer.poll();
				byte[] sendData = nextToSend.getBytes();
				
				DatagramPacket packet = new DatagramPacket(sendData, sendData.length, IP, Network.DEFAULTSERVERPORT);
				try {
					this.socket.send(packet);
				} catch (IOException e) {
					run = false;
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Attempts to send the String to the server
	 * @param dataToSend String The data
	 * @return boolean If the send was successful
	 */
	public synchronized boolean send(String dataToSend){
		try {
			this.buffer.put(dataToSend);
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

}
