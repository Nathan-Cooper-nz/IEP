package Network;

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
	
	

}
