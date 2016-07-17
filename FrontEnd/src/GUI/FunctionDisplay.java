package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;;

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