package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    	
    	Border boarder = LineBorder.createGrayLineBorder();
    	left.setBorder(boarder);
    	right.setBorder(boarder);
        
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
    	slider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				System.out.println("Slider chaneged to " + slider.getValue());
				
			}
    	});
    	Hashtable<Integer, JComponent> labels = new Hashtable<Integer, JComponent>();
    	labels.put(new Integer(0), new JLabel("0"));
    	labels.put(new Integer(30), new JLabel("30"));
    	slider.setLabelTable(labels);
    	slider.setPaintLabels(true);
    	JPanel sliderPanel = new JPanel();
    	JLabel voltLabel = new JLabel("Voltage");
    	sliderPanel.add(voltLabel);
    	sliderPanel.add(slider);
    	
    	JButton auto = new JButton("Autoset");
    	auto.addActionListener(new ActionListener(){
    		@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Autoset button pressed");
        	}
        });
    	
    	panel.add(text, gbc);
        panel.add(Box.createRigidArea(new Dimension(0,10)), gbc); //put a gap between the buttons
    	panel.add(auto, gbc);
    	panel.add(sliderPanel, gbc);
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