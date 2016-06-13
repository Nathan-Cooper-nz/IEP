package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

public class FunctionOptions extends JPanel {

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
        JSpinner ampl = new JSpinner();
        JSpinner peri = new JSpinner();
        JSeparator blank = new JSeparator(SwingConstants.HORIZONTAL); //TODO MAKE THIS WORK
        blank.setMaximumSize(new Dimension(Integer.MAX_VALUE,1));
        
        Border border = LineBorder.createGrayLineBorder();
        
        JLabel title = new  JLabel ("Function Generator"); //TODO make this title centered
        title.setPreferredSize(new Dimension(100,20));
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setBorder(border);
        
        String [] shapes = new String[] {"Select a Wave Shape","Sine","Square","Triangle"};
        JComboBox<String> waveShape = new JComboBox<>(shapes);

        //labels - TODO ALIGNMENT
        JLabel freqLabel = new JLabel("Frequency");
        freqLabel.setHorizontalAlignment(JLabel.CENTER);
        freqLabel.setVerticalAlignment(JLabel.CENTER);
        JLabel ampLabel = new JLabel("Amplitude");
        ampLabel.setHorizontalAlignment(JLabel.CENTER);
        ampLabel.setVerticalAlignment(JLabel.CENTER);
        JLabel perLabel = new JLabel("Period");
        perLabel.setHorizontalAlignment(JLabel.CENTER);
        perLabel.setVerticalAlignment(JLabel.CENTER);
        
        panel.add(title);
        panel.add(freqLabel);
        panel.add(freq);
        panel.add(blank);
        panel.add(ampLabel);
        panel.add(ampl);
        panel.add(blank);
        panel.add(perLabel);
        panel.add(peri);
        panel.add(blank);
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