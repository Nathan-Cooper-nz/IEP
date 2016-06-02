package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OscilloscopeOptions extends JTabbedPane {

    public OscilloscopeOptions(){        
        addTab("Volts/Div", getVoltsPerDiv());
        addTab("Trigger", getTrigger());
        addTab("Time/Div", getTimePerDiv());
        
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }
    
    public JPanel getVoltsPerDiv(){
    	JPanel panel = new JPanel();
    	return panel;
    }
    
    public JPanel getTrigger(){
    	JPanel panel = new JPanel();
    	return panel;
    }
    
    public JPanel getTimePerDiv(){
    	JPanel panel = new JPanel();
    	return panel;
    }
}