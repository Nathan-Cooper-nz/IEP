package Oscilloscope;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChannelTab extends JPanel{

	private int voltageOne;
	private int voltageTwo;
	
	public ChannelTab(){
    	GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
    	
    	JPanel left = getSubVoltagePanel("Channel 1", true);
    	JPanel right = getSubVoltagePanel("Channel 2", false);
        
    	addComp(this, left, 0, 0, 1, 1, 0.5, 1);
    	addComp(this, right, 1, 0, 1, 1, 0.5, 1);
    }
    
    /*
     * sub method to get the voltages for each channel
     */
    public JPanel getSubVoltagePanel(String name, boolean isLeft){
    	JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(0, 10, 0, 10)); //give 10px padding on the L+R
    	
    	JLabel text = new JLabel(name);
    
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
        couplePanel.add(acButton);
        couplePanel.add(dcButton);
        
      //pProbe
    	JPanel probePanel = new JPanel();
    	probePanel.add(new JLabel("Probe"));
    	JButton lowProbe = new JButton("1x");	
        acButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("1x button pressed");
        	}
        });
        JButton highProbe = new JButton("10x");	
        dcButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("10x button pressed");
        	}
        });
        probePanel.add(lowProbe);
        probePanel.add(highProbe);
        
        JPanel dPad = new DirectionPad();
        
    	JPanel right = new JPanel();   	
        right.setLayout(new GridBagLayout());
        
    	addComp(right, auto, 0, 0, 1, 1, 1, 0.05);
    	addComp(right, dPad, 0, 1, 1, 1, 1, 0.55);
        addComp(right, probePanel, 0, 2, 1, 1, 1, 0.2);
        addComp(right, couplePanel, 0, 3, 1, 1, 1, 0.2);
        
        addComp(panel, text, 0, 0, 1, 1, 1, 0.1);
        addComp(panel, right, 0, 1, 1, 1, 1, 0.9);

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
}
