package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FunctionDisplay extends JPanel {

    public FunctionDisplay(){

        JLabel label = new JLabel("Function Display");
        label.setPreferredSize(new Dimension(300,300));
        Border border = LineBorder.createGrayLineBorder();
        label.setBorder(border);
        setPreferredSize(new Dimension(300,300));
        add(label);
    }
}