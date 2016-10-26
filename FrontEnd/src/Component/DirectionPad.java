package Component;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * Created by nztyler on 24/10/16.
 */
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
