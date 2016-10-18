package Oscilloscope;

import java.util.ArrayList;

import Controller.GraphController;
import Controller.MeasureController;
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

	private Network net;

	private GraphController graphController;
	private MeasureController measureController;

	public DisplayThread(GraphController graphController, MeasureController measureController, Network net) {
		this.net = net;
		this.graphController = graphController;
		this.measureController = measureController;
	}

	@Override
	public void run(){
		try {
        	while (true) {
        		ArrayList<String> data = net.receive();
        		if(!data.isEmpty() && data.size() == 1){
            		graphController.setVoltage(data.get(0), null, null);
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
