package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
        addTab("Volts/Div", getVoltsPanel());
        addTab("Trigger", getTriggerPanel());
        addTab("Cursor", getCurserPanel());
        
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }
    
    //=========== Voltage ===========//
    public JPanel getVoltsPanel(){
    	JPanel panel = new JPanel();
    	GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
    	
    	JPanel left = getSubVoltagePanel("Channel 1", true);
    	JPanel right = getSubVoltagePanel("Channel 2", false);
        
    	addComp(panel, left, 0, 0, 1, 1, 0.5, 1);
    	addComp(panel, right, 1, 0, 1, 1, 0.5, 1);
    	
    	return panel;
    }
    
    /*
     * sub method to get the voltages for each channel
     */
    public JPanel getSubVoltagePanel(String name, boolean isLeft){
    	JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
    	
    	JLabel text = new JLabel(name);
    	
    	//voltage
    	JSlider slider = new JSlider(0, 30, 5); //format (min, max, default)
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
    	
    	//Autoset
    	JButton auto = new JButton("Autoset");
    	auto.addActionListener(new ActionListener(){
    		@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Autoset button pressed");
        	}
        });
    	
    	//Coupling
    	JPanel couplePanel = new JPanel();
    	couplePanel.add(new JLabel("Coupling"));
    	JButton acButton = new JButton("AC");	
        acButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("AC button pressed");
        	}
        });
        JButton dcButton = new JButton("DC");	
        dcButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("DC button pressed");
        	}
        });
        JButton groundButton = new JButton("GROUND");	
        groundButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("GOUND button pressed");
        	}
        });
        couplePanel.add(acButton);
        couplePanel.add(dcButton);
        couplePanel.add(groundButton);
        
        //position slider
        JSlider posSlider = new JSlider(JSlider.VERTICAL, 0, 100, 50); //format (min, max, default)
        posSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				System.out.println("Position chaneged to " + slider.getValue());
				
			}
    	});
    	Hashtable<Integer, JComponent> posLabels = new Hashtable<Integer, JComponent>();
    	posLabels.put(new Integer(0), new JLabel("0"));
    	posLabels.put(new Integer(100), new JLabel("100"));
    	posSlider.setLabelTable(posLabels);
    	posSlider.setPaintLabels(true);
        
    	JPanel right = new JPanel();
    	right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));    	
        addComp(right, auto, 0, 0, 1, 1, 1, 0.05);
        addComp(right, sliderPanel, 0, 1, 1, 1, 1, 0.1);
        addComp(right, couplePanel, 0, 2, 1, 1, 1, 0.2);
        
        addComp(panel, text, 0, 0, 1, 1, 1, 0.1);
        addComp(panel, posSlider, 0, 1, 1, 1, 0.1, 0.9);
        addComp(panel, right, 1, 1, 1, 1, 0.9, 0.9);

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
    	GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
    	
    	JPanel left = getSubCurserPanel("Channel 1", true);
    	JPanel right = getSubCurserPanel("Channel 2", false);
        
    	addComp(panel, left, 0, 0, 1, 1, 0.5, 1);
    	addComp(panel, right, 1, 0, 1, 1, 0.5, 1);
    	
    	return panel;
    }
    
    public JPanel getSubCurserPanel(String channel, boolean isLeft){
    	JPanel panel = new JPanel();
    	GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
    	
    	JLabel label = new JLabel(channel);
    	JSlider slider = new JSlider(0, 100, 50); //format (min, max, default)
    	slider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				System.out.println("Slider chaneged to " + slider.getValue());
				
			}
    	});
    	Hashtable<Integer, JComponent> labels = new Hashtable<Integer, JComponent>();
    	labels.put(new Integer(0), new JLabel("0"));
    	labels.put(new Integer(50), new JLabel("50"));
    	labels.put(new Integer(100), new JLabel("100"));
    	slider.setLabelTable(labels);
    	slider.setPaintLabels(true);
    	
    	addComp(panel, label, 0, 0, 1, 1, 1, 0.8);
    	addComp(panel, slider, 0, 1, 1, 1, 1, 0.2);
    	return panel;
    }
    
    /**
     * This is a helper method which is only used by this class to add
     * components to a panel
     * @param panel The panel that the component is being added to
     * @param component The component to be added
     * @param x Specifies the GridBagConstraints.gridx
     * @param y Specifies the GridBagConstraints.gridy
     * @param width Specifies the GridBagConstraints.gridwidth
     * @param height Specifies the GridBagConstraints.gridheight
     * @param weightX specifies the GridBagConstraints.weightx
     * @param weightY specifies the GridBagConstraints.weighty
     */
    private void addComp(JPanel panel, JComponent component,
    		int x, int y, int width, int height, double weightX, double weightY){
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = x;
    	gbc.gridy = y;
    	gbc.gridheight = height;
    	gbc.gridwidth = width;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.weightx = weightX;
    	gbc.weighty = weightY;
    	panel.add(component, gbc);	
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