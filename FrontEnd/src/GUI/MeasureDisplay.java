package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;;

public class MeasureDisplay extends JPanel{

	private double[] dataValues;

	private JLabel first;
	private JLabel second;
	private JLabel third;
	private JLabel fourth;
	private JLabel fifth;

	public MeasureDisplay(){

		add(new JLabel("Measure Panel"));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Border border = LineBorder.createGrayLineBorder();
		setBorder(border);

		first = new JLabel("1");
		second = new JLabel("2");
		third = new JLabel("3");
		fourth = new JLabel("4");
		fifth = new JLabel("5");

		add(getData("First: ", first));
		add(getData("Second: ", second));
		add(getData("Third: ", third));
		add(getData("Fourth: ", fourth));
		add(getData("Fifth: ", fifth));
	}

	public void update(double[] values) {
		// check that there are the right number of values
		if (values.length != 5) {
			throw new Error("There should be 5 values!");
		}

		dataValues = values;
		first.setText(Double.toString(values[0]));
		second.setText(Double.toString(values[1]));
		third.setText(Double.toString(values[2]));
		fourth.setText(Double.toString(values[3]));
		fifth.setText(Double.toString(values[4]));
	}

	public JPanel getData(String name, JLabel text){
		JPanel panel = new JPanel();

		Border border = LineBorder.createGrayLineBorder();
		panel.setBorder(border);

		JLabel title = new JLabel(name);
		text.setFont(text.getFont().deriveFont(18f));

		panel.add(title);
		panel.add(text);
		return panel;
	}
}