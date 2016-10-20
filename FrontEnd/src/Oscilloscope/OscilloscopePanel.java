package Oscilloscope;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This is the upper half of the Oscilloscope JPanel
 * and consists of the JPanel holding the graph and also 
 * the components on the left hand side
 * 
 * @author nztyler
 *
 */
public class OscilloscopePanel extends JPanel {
	
	private Graph display;
	
    public OscilloscopePanel(){
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
        setLayout(new GridBagLayout());
        
        //add the oscilloscope display and buttons
        display = new Graph(this);
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridBagLayout());
        
        add(display);
    }
    
    public JPanel getButtonPanel(){
    	JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        JButton saveButton = new JButton("Save");	//screenshot
        saveButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Save button pressed");
        		System.out.println("width: " + display.getWidth() + "\nheight: " + display.getHeight());
        	}
        });
        
        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Pause button pressed");
        	}
        });

        JPanel inner = new JPanel();
        inner.setLayout(new GridBagLayout());
        JComponent emptyBox = (JComponent) Box.createRigidArea(new Dimension(0,20));
        addComp(inner, pause, 0, 0, 1, 1, 1, 0.1);
        addComp(inner, emptyBox, 0, 1, 1, 1, 1, 0.1);
        addComp(inner, saveButton, 0, 2, 1, 1, 1, 0.1);
        addComp(inner, emptyBox, 0, 3, 1, 1, 1, 0.1);
        
        buttonPanel.add(inner, BorderLayout.SOUTH);
        return buttonPanel;
    }
    
    //This is the only getter we need
    public Graph getGraph(){
    	return display;
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
