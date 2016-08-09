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

	private OscilloscopeDisplay display;
	
	public OscilloscopeThread(OscilloscopeDisplay display){
		this.display = display;
	}
	
	@Override
	public void run(){
		try {
			
        	int position = 0;
        	
        	Network n = new Network();
        	while (true) {
        		ArrayList<String> data = n.receive();
        		if(data.size() > 0){
	    			String string = "";
	        		for (int i = 0; i < data.size(); i++) {
	    				string = data.get(i).trim();
	    				position++;
		        		double voltage = Double.parseDouble(string);
		                display.setVoltage(voltage);
	    			}

        		}
                //sleep(10);
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
