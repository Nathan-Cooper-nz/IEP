package Oscilloscope;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

public class DirectionPad extends JPanel{

	private JButton up;
	private JButton down;
	private JButton left;
	private JButton right;
	
	public DirectionPad(){
    	setLayout(new GridLayout(3,3));
    	
    	JLabel label = new JLabel();
		up = new BasicArrowButton(BasicArrowButton.NORTH);
        right = new BasicArrowButton(BasicArrowButton.EAST);
        left = new BasicArrowButton(BasicArrowButton.WEST);
        down = new BasicArrowButton(BasicArrowButton.SOUTH);

        add(label);
        add(up);
        add(label);
        add(label);
        add(left);
        add(label);
        add(right);
        add(label);
        add(down);
        add(label);
	}
	
	public JButton getUp(){
		return up;
	}
	
	public JButton getDown(){
		return down;
	}
	
	public JButton getLeft(){
		return left;
	}
	
	public JButton getRight(){
		return right;
	}
	
}
