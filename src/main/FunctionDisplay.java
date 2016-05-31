package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FunctionDisplay extends JPanel {

    public FunctionDisplay(){
    	add(new JLabel("Function display"));
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }
}