package Oscilloscope;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TriggerTab extends JPanel{
	
	private String butt1,butt2;

	public TriggerTab(){
		JLabel trigger = new JLabel("Trigger");
		JPanel tPanel = getRadio("Edge", "Level");
		
		JLabel channel = new JLabel("Channel");
		JPanel cPanel = getRadio("Channel 1", "Channel 2");
		
		JLabel slope = new JLabel("Slope");
		JPanel sPanel = getRadio("Rising", "Falling");
		
		JLabel mode = new JLabel("Mode");
		JPanel mPanel = getRadio("Auto","Normal");
		
		JLabel coupling = new JLabel("Coupling");
		JPanel coPanel = getRadio("AC","DC");
		 
		setLayout(new GridLayout(5,0));

		add(trigger);
		add(tPanel);
		
		add(channel);
		add(cPanel);
		
		add(slope);
		add(sPanel);
		
		add(mode);
		add(mPanel);
		
		add(coupling);
		add(coPanel);

	}
	
	public JPanel getRadio(String butt1,String butt2){
		JPanel radioButts = new JPanel();
		JRadioButton b1 = new JRadioButton(butt1);
		JRadioButton b2 = new JRadioButton(butt2);
		
		ButtonGroup gr = new ButtonGroup();
		gr.add(b1);
		gr.add(b2);
		
		return radioButts;
	}
}
