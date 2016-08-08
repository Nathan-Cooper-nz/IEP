package Oscilloscope;

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
        		double voltage = Math.sin(position*2*Math.PI/50) * (10);
                display.setVoltage(voltage);
                sleep(50);
                
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
