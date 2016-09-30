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
	private Network n;
	
	
	private static String SINE = "0.0,3.2539,5.9452,7.6085,7.9562,6.9282,4.7023,1.6633,-1.6633,-4.7023,"
			+ "-6.9282,-7.9562,-7.6085,-5.9452,-3.2539,0.0,3.2539,5.9452,7.6085,7.9562,6.9282,4.7023,"
			+ "1.6633,-1.6633,-4.7023,-6.9282,-7.9562,-7.6085,-5.9452,-3.2539,0.0,3.2539,5.9452,7.6085,"
			+ "7.9562,6.9282,4.7023,1.6633,-1.6633,-4.7023";
	
	private static String COSINE = "8.0,7.3084,5.353,2.4721,-0.8362,-4.0,-6.4721,-7.8252,-7.8252,-6.4721,"
			+ "-4.0,-0.8362,2.4721,5.353,7.3084,8.0,7.3084,5.353,2.4721,-0.8362,-4.0,-6.4721,-7.8252,-7.8252,"
			+ "-6.4721,-4.0,-0.8362,2.4721,5.353,7.3084,8.0,7.3084,5.353,2.4721,-0.8362,-4.0,-6.4721,-7.8252,"
			+ "-7.8252,-6.4721";

	private static String TANGENT = "0.0,3.5618,8.8849,24.6215,-76.1149,-13.8564,-5.8123,-1.7005,1.7005,5.8123,"
			+ "13.8564,76.1149,-24.6215,-8.8849,-3.5618,0.0,3.5618,8.8849,24.6215,-76.1149,-13.8564,-5.8123,"
			+ "-1.7005,1.7005,5.8123,13.8564,76.1149,-24.6215,-8.8849,-3.5618,0.0,3.5618,8.8849,24.6215,-76.1149,"
			+ "-13.8564,-5.8123,-1.7005,1.7005,5.8123";

	public OscilloscopeThread(GraphDisplay display){
		this.display = display;
		this.n= new Network();
	}

	@Override
	public void run(){
		try {
        	while (true) {
        		ArrayList<String> data = n.receive();        		
        		if(!data.isEmpty()){
        			System.out.println(data.get(0));
        			System.out.println(data.get(0).split(",").length);
            		display.setVoltage(data.get(0), null, null);

        		}
//        		display.setVoltage(SINE, COSINE, TANGENT);
//                sleep(5);
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public Network getNetwork() {
		// TODO Auto-generated method stub
		return n;
	}

}
