package Oscilloscope;

import java.util.ArrayList;

import Network.Network;

/**
 * This is the Thread for the Oscilloscope. This thread
 * should read in the changes to be made from the sockets
 * and sends the changes to the Oscilloscope Display
 *
 * @author nztyler
 *
 */
public class DisplayThread extends Thread{

	private Graph display;
	private Network net;

	public DisplayThread(Graph display){
		this.display = display;
		this.net= new Network();
	}

	@Override
	public void run(){
		try {
        	while (true) {
        		ArrayList<String> data = net.receive();
        		if(!data.isEmpty()){
        			System.out.println(data.get(0));
        			System.out.println(data.get(0).split(",").length);
            		display.setVoltage(data.get(0), null, null);
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public Network getNetwork() {
		// TODO Auto-generated method stub
		return net;
	}

}
