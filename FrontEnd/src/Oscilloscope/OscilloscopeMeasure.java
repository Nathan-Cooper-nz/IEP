package Oscilloscope;

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

public class OscilloscopeMeasure extends JPanel{

	public OscilloscopeMeasure(){

		add(new JLabel("Measure Panel"));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Border border = LineBorder.createGrayLineBorder();
		setBorder(border);

		JPanel first = getData("First");
		JPanel second = getData("Second");
		JPanel third = getData("Third");
		JPanel fourth = getData("Fourth");
		JPanel fifth = getData("Fifth");

		add(first);
		add(second);
		add(third);
		add(fourth);
		add(fifth);
	}

	public JPanel getData(String name){
		JPanel panel = new JPanel();
		Border border = LineBorder.createGrayLineBorder();
		panel.setBorder(border);
		JLabel label = new JLabel(name);
		panel.add(label);
		return panel;
	}
}