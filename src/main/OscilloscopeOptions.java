package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by nztyler on 29/05/16.
 */
public class OscilloscopeOptions extends JPanel {

    public OscilloscopeOptions(){
        JLabel label = new JLabel("Oscilloscope Options");
        label.setPreferredSize(new Dimension(600,300));
        Border border = LineBorder.createGrayLineBorder();
        label.setBorder(border);
        setPreferredSize(new Dimension(600,300));
        add(label);
    }
}