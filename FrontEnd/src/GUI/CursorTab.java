package GUI;

import Component.UpDown;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class CursorTab extends JPanel{
//./
	public CursorTab(){
    	GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(new EmptyBorder(10, 10, 10, 10));
    	
        JPanel upper = new JPanel();
        final String amp = "Amplitude";
        final String time = "Time";
        JButton ampOrTime = new JButton("amp");
        ampOrTime.addActionListener(new ActionListener(){
    		@Override
        	public void actionPerformed(ActionEvent e){
    			if(((String)e.getActionCommand()).equals(amp)){
    				ampOrTime.setText(time);
    			} else if(((String)e.getActionCommand()).equals(time)) {
    				ampOrTime.setText(amp);
    			}
        	}
        });
        upper.add(ampOrTime);
        
    	JPanel left = getSubCurserPanel("Cursor 1", true);
    	JPanel right = getSubCurserPanel("Cursor 2", false);

    	addComp(this, left, 0, 0, 1, 1, 0.5, 1);
    	addComp(this, right, 1, 0, 1, 1, 0.5, 1);
    }
    
    public JPanel getSubCurserPanel(String channel, boolean isLeft){
    	JPanel panel = new JPanel();
    	GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
    	
    	JLabel label = new JLabel(channel);
    	JPanel dPad = new UpDown.DirectionPad();
    	
    	JPanel pad = new JPanel();
    	pad.setLayout(new GridBagLayout());
    	// source
    	JLabel sourceLabel = new JLabel("Source");
    	JPanel sources = new JPanel();
    	sources.setLayout(new GridBagLayout());
    	JRadioButton ch1 = new JRadioButton("CH1");
    	JRadioButton ch2 = new JRadioButton("CH2");
    	ButtonGroup group = new ButtonGroup();
    	group.add(ch1);
    	group.add(ch2);
    	
    	addComp(sources, sourceLabel, 0, 0, 1, 1, 0.3, 1);
    	addComp(sources, ch1, 1, 0, 1, 1, 0.3, 1);
    	addComp(sources, ch2, 2, 0, 1, 1, 0.4, 1);
    	
    	addComp(pad, sources, 0, 0, 1, 1, 0.4, 1);
    	addComp(pad, dPad, 1, 0, 1, 1, 0.6, 1);
    	
    	addComp(panel, label, 0, 0, 1, 1, 1, 0.3);
    	addComp(panel, pad, 0, 1, 1, 1, 1, 0.7);
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
