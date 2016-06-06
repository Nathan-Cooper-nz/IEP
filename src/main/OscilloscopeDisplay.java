package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OscilloscopeDisplay extends JPanel{

	private boolean channelOneOn = false;
	private boolean channelTwoOn = false;
	
    public OscilloscopeDisplay(){
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //add the oscilloscope display and buttons
        gbc.gridx = 0;
        gbc.weightx = 0.9;
        gbc.weighty = 1;
        add(getDisplayPanel(), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.1;
        add(getButtonPanel(), gbc);
    }
    
    public JPanel getDisplayPanel(){
    	JPanel panel = new JPanel();
    	panel.add(new JLabel("Oscilloscope display"));
    	return panel;
    }
    
    public JPanel getButtonPanel(){
    	JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton saveButton = new JButton("Save");	//screenshot
        saveButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Save button pressed");
        		channelOneOn = false;
        		channelTwoOn = false;
        	}
        });
        ButtonGroup channel = new ButtonGroup();
        JButton channelOne = new JButton("CH1");
        channelOne.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("First channel selected");
        		channelOneOn = true;
        		channelTwoOn = false;
        		
        	}
        });
        JButton channelTwo = new JButton("CH2");
        channelTwo.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Second channel selected");
        		channelOneOn = false;
        		channelTwoOn = true;
        	}
        });
        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Pause button pressed");
        	}
        });

        channel.add(channelOne);
        channel.add(channelTwo);
  
        buttonPanel.add(channelOne);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPanel.add(channelTwo);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,20)));
        buttonPanel.add(pause);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPanel.add(saveButton);
        return buttonPanel;
    }
    
}
