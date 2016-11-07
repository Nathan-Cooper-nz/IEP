package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;

public class FunctionOptions extends JPanel {

	public int frequency;
	public int amplitude;
	public int period;

	public FunctionOptions(){
		GridBagConstraints gbc = new GridBagConstraints();
		//setLayout(new GridBagLayout());
		add(getButtons(), gbc);
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

		/*JComboBox<String> waveShape = new JComboBox<String>();
		waveShape.addItem("Choose a wave shape");
		waveShape.addItem("Sine");
		waveShape.addItem("Square");
		waveShape.addItem("Triangle");
		DefaultListSelectionModel model = new DefaultListSelectionModel();
		model.addSelectionInterval(1, 3);
		//model.addSelectionInterval(3, 3);
		EnabledComboBoxRenderer enableRend = new EnabledComboBoxRenderer(model);
		waveShape.setRenderer(enableRend);
		
		waveShape.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> ws = (JComboBox<String>) e.getSource();
				System.out.println("Shape: "+ws.getSelectedItem());
			}
		});*/

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
		
		JLabel f = new JLabel("Frequency: ");
		
		JTextField fr = new JTextField();
		panel.add(f);
		panel.add(fr);
		panel.add(upDownPanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		JLabel bl = new JLabel("");
		//panel.add(bl);
		
		
		JLabel a = new JLabel("Amplitude: ");
		JTextField am = new JTextField();
		panel.add(a);
		panel.add(am);
		panel.add(upDownPanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		//panel.add(bl);
		
		JLabel p = new JLabel("Period: ");
		JTextField pe = new JTextField();
		panel.add(p);
		panel.add(pe);
		panel.add(upDownPanel());
		
		/*JLabel freqLabel = new JLabel("Frequency (Hz)");
		freqLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel ampLabel = new JLabel("Amplitude");
		ampLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel perLabel = new JLabel("Period");
		perLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JSpinner freq = new JSpinner();
		freq.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner freq = (JSpinner) e.getSource();
				System.out.println("Freq value: "+freq.getValue());
			}
		});
		JSpinner ampl = new JSpinner();
		ampl.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner ampl = (JSpinner) e.getSource();
				System.out.println("Ampl value: "+ampl.getValue());
			}
		});
		JSpinner peri = new JSpinner();
		peri.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner peri = (JSpinner) e.getSource();
				System.out.println("Peri value: "+peri.getValue());
			}
		});
		
		
		Dimension fd = freq.getPreferredSize();
		fd.height = 25;
		freq.setPreferredSize(fd);
		
		panel.add(freqLabel);
		panel.add(freq);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(ampLabel);
		panel.add(ampl);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(perLabel);
		panel.add(peri);*/
		
		return panel;
	}
	
	public JPanel upDownPanel(){
		JPanel upDown = new JPanel();
		upDown.setLayout(new GridLayout(2,1));
		JButton up = new BasicArrowButton(BasicArrowButton.NORTH);
		JButton down = new BasicArrowButton(BasicArrowButton.SOUTH);
		Component blank = new JLabel("");
		//upDown.add(blank);
		upDown.add(up);
		//upDown.add(blank);
		//upDown.add(blank);
		upDown.add(down);
		return upDown;
	}
}