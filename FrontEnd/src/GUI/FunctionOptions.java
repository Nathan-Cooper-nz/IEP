package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

		JComboBox<String> waveShape = new JComboBox<String>();
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