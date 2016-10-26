package Component;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

public class UpDown {
	
	private JButton up;
    private JButton down;
	
	public UpDown(){
		up = new BasicArrowButton(BasicArrowButton.NORTH);
		down = new BasicArrowButton(BasicArrowButton.SOUTH);		
	}

	public JButton getUp() {
        return up;
    }

    public JButton getDown() {
        return down;
    }

	public JPanel getUD(){
		JPanel upDown = new JPanel();
		upDown.setLayout(new GridLayout(2,1));
		upDown.add(up);
		upDown.add(down);
		return upDown;
	}
}
