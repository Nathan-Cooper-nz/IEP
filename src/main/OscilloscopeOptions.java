package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

public class OscilloscopeOptions extends JPanel {

    public OscilloscopeOptions(){
    	add(new JLabel("Oscilloscope options"));
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }
}