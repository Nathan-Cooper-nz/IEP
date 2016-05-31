package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OscilloscopeDisplay extends JPanel{

    public OscilloscopeDisplay(){
        JLabel label = new JLabel("Oscilloscope Display");
        label.setPreferredSize(new Dimension(600,400));
        Border border = LineBorder.createGrayLineBorder();
        label.setBorder(border);
        setPreferredSize(new Dimension(600,400));
        add(label);
    }
}