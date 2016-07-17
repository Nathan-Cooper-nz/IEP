package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Graphics;;

public class FunctionDisplay extends JPanel{

	public FunctionDisplay(){
		add(new JLabel("Function display"));
		Border border = LineBorder.createGrayLineBorder();
		setBorder(border);
		//GraphCanvas gc = new GraphCanvas();
		//gc.paint(new Graphics());
		//add(getDisplay());
	}

	public JPanel getDisplay(){
		JPanel panel = new JPanel();
		int w = panel.getWidth();
		int h = panel.getHeight();
		int startx=0;

		return panel;

	}
}