package Main;

import Controller.GraphController;
import Controller.MeasureController;
import GUI.IEPframe;
import Oscilloscope.DisplayThread;

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
            	
            	IEPframe frame = new IEPframe();

                //make the controllers
                GraphController graph = new GraphController(frame.getGraph());
                MeasureController measure = new MeasureController(frame.getMeasureDisplay(), frame.getMeasureTab());

            	//start the oscilloscope thread
                DisplayThread thread = new DisplayThread(graph, measure, frame.getNetwork());
            	thread.start();
            }
        });
    }
}
