package Oscilloscope;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TriggerTab extends JPanel{

	public TriggerTab(){
		JLabel trigger = new JLabel("Trigger");
		JRadioButton edge = new JRadioButton("Edge");
		JRadioButton level = new JRadioButton("Level");
		ButtonGroup tGroup = new ButtonGroup();
		tGroup.add(edge);
		tGroup.add(level);
		
		JLabel channel = new JLabel("Channel");
		JRadioButton c1 = new JRadioButton("Channel 1");
		JRadioButton c2 = new JRadioButton("Channel 2");
		ButtonGroup cGroup = new ButtonGroup();
		cGroup.add(c1);
		cGroup.add(c2);
		
		JLabel slope = new JLabel("Slope");
		JRadioButton rising = new JRadioButton("Rising");
		JRadioButton falling = new JRadioButton("Falling");
		ButtonGroup sGroup = new ButtonGroup();
		sGroup.add(rising);
		sGroup.add(falling);
		
		JLabel mode = new JLabel("Mode");
		JRadioButton auto = new JRadioButton("Auto");
		JRadioButton normal = new JRadioButton("Normal");
		ButtonGroup mGroup = new ButtonGroup();
		mGroup.add(auto);
		mGroup.add(normal);
		
		JLabel coupling = new JLabel("Coupling");
		JRadioButton ac = new JRadioButton("AC");
		JRadioButton dc = new JRadioButton("DC");
		ButtonGroup coGroup = new ButtonGroup();
		coGroup.add(ac);
		coGroup.add(dc);
		
		setLayout(new GridLayout(5,0));

		add(trigger);
		add(edge);
		add(level);
		
		add(channel);
		add(c1);
		add(c2);
		
		add(slope);
		add(rising);
		add(falling);
		
		add(mode);
		add(auto);
		add(normal);
		
		add(coupling);
		add(ac);
		add(dc);

	}
}
