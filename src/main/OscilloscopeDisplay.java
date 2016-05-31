package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

public class OscilloscopeDisplay extends JPanel{

    public OscilloscopeDisplay(){
    	add(new JLabel("Oscilloscope display"));
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }
}