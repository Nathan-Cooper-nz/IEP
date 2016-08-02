package Oscilloscope;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MeasureTab extends JPanel{

	public MeasureTab(){
		setLayout(new GridBagLayout());
		addComp(this, getSubMeasurePanel(), 0, 0, 1 , 1, 0.2, 1);
		addComp(this, getSubMeasurePanel(), 1, 0, 1 , 1, 0.2, 1);
		addComp(this, getSubMeasurePanel(), 2, 0, 1 , 1, 0.2, 1);
		addComp(this, getSubMeasurePanel(), 3, 0, 1 , 1, 0.2, 1);
		addComp(this, getSubMeasurePanel(), 4, 0, 1 , 1, 0.2, 1);
	}

	public JPanel getSubMeasurePanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		Border border = LineBorder.createGrayLineBorder();
		panel.setBorder(border);

		JLabel label = new JLabel("CH1");
		String [] items = new String[] {"Freq","RMS","P.P Voltage"}; 
		JComboBox<String> itemDropdown = new JComboBox<>(items);
		itemDropdown.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				JComboBox<String> things = source;
				System.out.println("Shape: "+things.getSelectedItem());
			}
		});
		JTextArea text = new JTextArea();
		text.setText("DATA");
		text.setEditable(false);

		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.add(label);
		left.add(itemDropdown);
		panel.add(left);
		panel.add(text);
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
    public void addComp(JPanel panel, JComponent component,
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
