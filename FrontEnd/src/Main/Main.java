package Main;

import GUI.IEPframe;

import java.awt.EventQueue;

public class Main {
	
    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	//Something to make the GUI look nicer
            	try { 
            	    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
            	
            	GUI.IEPframe frame = new IEPframe();
            	
            	//start the oscilloscope thread
            	frame.getOscilloscopePanel().getOscilloscopeDisplay().getOscilloscopeThread().start();
            }
        });
    }
}
