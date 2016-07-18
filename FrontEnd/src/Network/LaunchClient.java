package Network;

import java.util.ArrayList;

public class LaunchClient {

	public static void main(String[] args) {
		Network n = new Network();
		
		firstConnect(n);
		
		while(true){
			ArrayList<String> data = n.receive();
			System.out.println(data.toString());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void firstConnect(Network n) {
		ArrayList<String> data = new ArrayList<>();
		
		do{
			n.send("helloWorldThisIsWaldo");
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			data = n.receive();
		}while(data.size() < 1);
		
	}

}
