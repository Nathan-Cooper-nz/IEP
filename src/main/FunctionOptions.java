package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FunctionOptions extends JPanel {

    public FunctionOptions(){

        JLabel label = new JLabel("Function Options");
        label.setPreferredSize(new Dimension(300,400));
        Border border = LineBorder.createGrayLineBorder();
        label.setBorder(border);
        setPreferredSize(new Dimension(300,400));
        add(label);
    }
}