package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class OscilloscopeOptions extends JTabbedPane {

	/*
	public double timePerDiv;
	private double triggerLevel;
	
	//CHANNEL ONE
	private double voltageOne;
	private boolean isOnOne;
	private Coupling couplingOne;
	
	//CHANNEL TWO
	private double voltageTwo;
	private boolean isOnTwo;
	private Coupling couplingTwo;
	*/
	
    public OscilloscopeOptions(){        
        addTab("Volts/Div shit", getVoltsPanel());
        addTab("Trigger shit", getTriggerPanel());
        addTab("Cursor shit", getCurserPanel());
        
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }
    
    //=========== Voltage ===========//
    public JPanel getVoltsPanel(){
    	JPanel panel = new JPanel();
    	
    	JPanel left = getSubVoltagePanel("Channel 1");
    	JPanel right = getSubVoltagePanel("Channel 2");
        
    	panel.add(left);
    	panel.add(right);
    	
    	return panel;
    }
    
    /*
     * 
     */
    public JPanel getSubVoltagePanel(String name){
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridwidth = GridBagConstraints.REMAINDER;
    	
    	JLabel text = new JLabel(name);
    	//slider with min , max 30, default 5
    	JSlider slider = new JSlider(0, 30, 5);
    	JButton auto = new JButton("Autoset");
    	auto.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Autoset button pressed");
        	}
        });
    	
    	panel.add(text, gbc);
        panel.add(Box.createRigidArea(new Dimension(0,10)), gbc); //put a gap between the buttons
    	panel.add(auto, gbc);
    	panel.add(slider, gbc);
    	return panel;
    }
    
  //=========== Triggering ===========//
    public JPanel getTriggerPanel(){
    	JPanel panel = new JPanel();
    	return panel;
    }
    
  //=========== Curser ===========//
    public JPanel getCurserPanel(){
    	JPanel panel = new JPanel();
    	return panel;
    }
   
    
    /**
     * This just makes it clearer that there should always be a specific
     * type of coupling
     * @author nztyler
     *
     */
    public enum Coupling {
    	AC, DC, GROUND
    }
}