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
public class OscilloscopeThread extends Thread{

	private GraphDisplay display;

	private static String STRING1 = "-10,-8,-6,-4,-2,0,2,4,6,8,10";
	private static String STRING2 = "10,8,6,4,2,0,-2,-4,-6,-8,-10";
	
	
	public OscilloscopeThread(GraphDisplay display){
		this.display = display;
	}

	@Override
	public void run(){
		try {
			String toSend = STRING1;
        	// Network n = new Network();
        	while (true) {
//        		ArrayList<String> data = n.receive();
//        		if(data.size() > 0){
//	    			String string = "";
//	        		for (int i = 0; i < data.size(); i++) {
//	    				string = data.get(i).trim();
//		        		double voltage = Double.parseDouble(string);
//		                display.addVoltage(voltage);
//	    			}
//
//        		}
        		
        		display.setVoltage(toSend);
                sleep(5000);
                if (toSend.equals(STRING1)) { 
                	toSend = STRING2;
                } else {
                	toSend = STRING1;
                }
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
