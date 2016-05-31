package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

public class FunctionOptions extends JPanel {

    public FunctionOptions(){
    	add(new JLabel("Function options"));
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }
}