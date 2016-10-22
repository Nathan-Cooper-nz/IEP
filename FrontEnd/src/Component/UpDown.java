package Component;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

/*
 * Creates a set of two buttons to control the increasing and decreasing of FG functions
 */

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
	

	public static class DirectionPad extends JPanel{

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

            add(new JLabel());
            add(up);
            add(new JLabel());
            add(new JLabel());
            add(left);
            add(new JLabel());
            add(right);
            add(new JLabel());
            add(new JLabel());
            add(down);
            add(new JLabel());
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
}
