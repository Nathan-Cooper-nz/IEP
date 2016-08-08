package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

public class UpDown {
	
	JButton up,down;
	
	public UpDown(){
		up = new BasicArrowButton(BasicArrowButton.NORTH);
		down = new BasicArrowButton(BasicArrowButton.SOUTH);		
	}
	
	public JPanel getUD(){
		JPanel upDown = new JPanel();
		upDown.setLayout(new GridLayout(2,1));
		upDown.add(up);
		upDown.add(down);
		return upDown;
	}

}
