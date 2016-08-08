package Oscilloscope;

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
        	
        	while (true) {
        		
        		position++;
        		double voltage = Math.sin(position*2*Math.PI/120) * (10);
                display.setVoltage(voltage);
                sleep(25);
                
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
