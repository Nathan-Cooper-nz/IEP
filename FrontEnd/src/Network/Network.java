package Network;
//https://systembash.com/a-simple-java-udp-server-and-udp-client/

import java.util.concurrent.ArrayBlockingQueue;

public class Network {
	
	ClientSender sender;
	ClientListener listener;
	
	public static final String UPDATESTRING = "GETANUPDATENOW";
	
	
	public Network() {
		this.sender = new ClientSender();
		this.listener = new ClientListener();
		
		this.sender.start();
		this.listener.start();
	}
	
	public void closeAll(){
		try {
			sender.close();
			listener.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void send(String s){
		this.sender.send(s);
	}
	
	public String receive(){
		this.sender.send(UPDATESTRING);
		return this.listener.poll();
	}

}
