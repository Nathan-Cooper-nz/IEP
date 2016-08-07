package Network;

import java.util.ArrayList;
import java.util.Iterator;

public class LaunchClient {

	public static void main(String[] args) {
		Network n = new Network();
		
		
		while(true){
			ArrayList<String> data = n.receive();
			for (int i = 0; i < data.size(); i++) {
				String string = data.get(i).trim();//Trim is needed as the data includes a lot of white space from the buffer
				System.out.println("Ping: " + (System.currentTimeMillis() - Long.parseLong(string)) + "ms");
			}
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

	}


}
