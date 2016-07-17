package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

		//all the spinners
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


		JLabel title = new  JLabel ("Function Generator");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);


		String [] shapes = new String[] {"Select a Wave Shape","Sine","Square","Triangle"}; //shape dropdowns
		JComboBox<String> waveShape = new JComboBox<>(shapes);//need to not be able to select the first element
		waveShape.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> ws = (JComboBox<String>) e.getSource();
				System.out.println("Shape: "+ws.getSelectedItem());
			}
		});
		JLabel freqLabel = new JLabel("Frequency (Hz)");
		freqLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel ampLabel = new JLabel("Amplitude");
		ampLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel perLabel = new JLabel("Period");
		perLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		//add all the elements to the panel
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(freqLabel);
		panel.add(freq);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(ampLabel);
		panel.add(ampl);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(perLabel);
		panel.add(peri);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(waveShape);        

		/*JLabel frequency,amplitude,period;
    	JSeparator separator = new JSeparator(JSeparator.VERTICAL);
    	add(new JLabel("Function options"));
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);

        //SpinnerNumberModel model = new SpinnerNumberModel();
        JSpinner freq = new JSpinner();
        JSpinner ampl = new JSpinner();
        JSpinner peri = new JSpinner();
        panel.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        panel.add(frequency=new JLabel("Frequency"),"wrap");
        panel.add(freq);
        panel.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        panel.add(amplitude=new JLabel("Amplitude"),"wrap");
        panel.add(ampl);
        panel.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        panel.add(period=new JLabel("Period"),"wrap");
        panel.add(peri);*/

		return panel;
	}
}