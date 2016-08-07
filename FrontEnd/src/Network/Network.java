package Network;
//https://systembash.com/a-simple-java-udp-server-and-udp-client/

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Network {
	public static final int DEFAULTSERVERPORT = 5843;
	
	ClientSender sender;
	ClientListener listener;
	
	public static final String UPDATESTRING = "GETANUPDATENOW";
	
	
	public Network() {
		ClientSender sender = new ClientSender();
		this.listener = new ClientListener();
		
		int port = this.listener.getPort();
		String portS = Integer.toString(port);
		
		System.out.println(portS);
		
		for(int i = 0; i < 5; i++){
			sender.send(portS);
		}
		
		this.sender = sender;
		
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
	
	public ArrayList<String> receive(){
		return listener.poll();
	}

}
