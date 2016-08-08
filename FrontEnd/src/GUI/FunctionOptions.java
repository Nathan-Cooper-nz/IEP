package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListSelectionModel;
import javax.swing.Icon;
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
		setLayout(new GridBagLayout());
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
		JTextField fr = new JTextField("0");
		frequency = Integer.parseInt(fr.getText());
		UpDown fud = new UpDown();
		fud.up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = Integer.parseInt(fr.getText());
				t++;
				fr.setText(t + "");
			}
		});
		fud.down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = Integer.parseInt(fr.getText());
				t = t-1;
				fr.setText(t + "");
			}
		});
		
		panel.add(f);
		panel.add(fr);
		panel.add(fud.getUD());
		panel.add(Box.createRigidArea(new Dimension(0,10)));	
		
		JLabel a = new JLabel("Amplitude: ");
		JTextField am = new JTextField("0");
		amplitude = Integer.parseInt(am.getText());
		UpDown aud = new UpDown();
		
		aud.up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = Integer.parseInt(am.getText());
				t++;
				am.setText(t + "");
			}
		});
		aud.down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = Integer.parseInt(am.getText());
				t = t-1;
				am.setText(t + "");
			}
		});
		
		panel.add(a);
		panel.add(am);
		panel.add(aud.getUD());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		
		JLabel p = new JLabel("Period: ");
		JTextField pe = new JTextField("0");
		period = Integer.parseInt(pe.getText());
		UpDown pud = new UpDown();
		
		pud.up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = Integer.parseInt(pe.getText());
				t++;
				pe.setText(t + "");
			}
		});
		pud.down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = Integer.parseInt(pe.getText());
				t = t-1;
				pe.setText(t + "");
			}
		});
		
		panel.add(p);
		panel.add(pe);
		panel.add(pud.getUD());
		
		return panel;
	}
}