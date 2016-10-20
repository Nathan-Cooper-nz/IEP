package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Network.Network;

import Component.UpDown;

public class FunctionOptions extends JPanel {

	private static final int FREQUENCY_INDEX = 0;
	private static final int AMPLITUDE_INDEX = 1;
	private static final int PERIOD_INDEX = 2;

	private int frequency;
	private int amplitude;
	private int period;
	private String type;
	
	private Network net;

	public FunctionOptions(Network net){
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		add(getButtons(), gbc);

		frequency = 1;
		amplitude = 1;
		period = 1;
		type = "sine";
		this.net = net;
	}

	public JPanel getButtons(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.setLayout(new GridLayout(16,1));
		//panel.setLayout(new GridLayout(5 ,0));

		JLabel title = new  JLabel ("Function Generator");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ButtonGroup waves = new ButtonGroup();
		JRadioButton sine = new JRadioButton("Sine");//TODO REPLACE WITH PICTURES
		JRadioButton tri = new JRadioButton("Triangle");
		JRadioButton sq = new JRadioButton("Square");
		waves.add(sine);
		waves.add(tri);
		waves.add(sq);

		ActionListener JRadioListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				type = arg0.getActionCommand();
				sendUpdate();
			}
		};
		
		sine.addActionListener(JRadioListener);
		tri.addActionListener(JRadioListener);
		sq.addActionListener(JRadioListener);
		
		
		//add all the elements to the panel
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		JLabel wave = new JLabel("Select a wave shape:");
		panel.add(wave);
		panel.add(sine);
		panel.add(tri);
		panel.add(sq);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(spinnerPanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		//panel.add(waveShape);        

		//panel.add(upDownPanel());	
		return panel;
	}

	public JPanel spinnerPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,0));

		// Frequency
		JLabel f = new JLabel("Frequency: ");
		JTextField fr = new JTextField("1");
		frequency = Integer.parseInt(fr.getText());
		UpDown fud = new UpDown();

		setActionListener(fud.getUp(), fr, 1, FREQUENCY_INDEX);
		setActionListener(fud.getDown(), fr, -1, FREQUENCY_INDEX);
		
		panel.add(f);
		panel.add(fr);
		panel.add(fud.getUD());
		panel.add(Box.createRigidArea(new Dimension(0,10)));	

		// Amplitude
		JLabel a = new JLabel("Amplitude: ");
		JTextField am = new JTextField("1");
		amplitude = Integer.parseInt(am.getText());
		UpDown aud = new UpDown();

		setActionListener(aud.getUp(), am, 1, AMPLITUDE_INDEX);
		setActionListener(aud.getDown(), am, -1, AMPLITUDE_INDEX);
		
		panel.add(a);
		panel.add(am);
		panel.add(aud.getUD());
		panel.add(Box.createRigidArea(new Dimension(0,10)));

		// Period
		JLabel p = new JLabel("Period: ");
		JTextField pe = new JTextField("1");
		period = Integer.parseInt(pe.getText());
		UpDown pud = new UpDown();

		setActionListener(pud.getUp(), pe, 1, PERIOD_INDEX);
		setActionListener(pud.getDown(), pe, -1, PERIOD_INDEX);
		
		panel.add(p);
		panel.add(pe);
		panel.add(pud.getUD());
		
		return panel;
	}

	/**
	 * A method to add the action listeners to the buttons
	 * @param button The button that the action listener is being added to
	 * @param text The value displayed in the text box
	 * @param change Either positive or negative 1 to represent change
	 * @param field 0 = Frequency, 1 = Amplitude, 2 = Period
	 */
	public void setActionListener(JButton button, JTextField text, int change, int field) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = Integer.parseInt(text.getText());
				t += change;
				text.setText(Integer.toString(t));
				switch(field) {
					case FREQUENCY_INDEX: frequency = t; break;
					case AMPLITUDE_INDEX: amplitude = t; break;
					case PERIOD_INDEX: period = t; break;
				}
				sendUpdate();
			}
		});
	}

	private void sendUpdate(){
		StringBuilder string = new StringBuilder();
		string.append("fcnGen,");
		string.append(type);
		string.append(',');
		string.append(amplitude);
		string.append(',');
		string.append(frequency);
		string.append(',');
		string.append(period);
		net.send(string.toString());
		
	}
	
	
}