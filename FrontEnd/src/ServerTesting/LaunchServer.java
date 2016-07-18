package ServerTesting;

import java.io.IOException;

public class LaunchServer {

	public static void main(String[] args) {
		try {
			new ServerThread().start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
