package GUI;

import java.awt.Dimension;

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

public class FunctionDisplay extends JPanel{

	public FunctionDisplay(){
		//add(new JLabel("Function display"));
		Border border = LineBorder.createGrayLineBorder();
		setBorder(border);
		//GraphCanvas gc = new GraphCanvas();
		//gc.paint(new Graphics());
		add(getDisplay());
	}

	public JPanel getDisplay(){
		JPanel panel = new JPanel();
//		int w = panel.getWidth();
//		int h = panel.getHeight();
//		int startx=0;
		
		XYSeries voltages = new XYSeries("Voltages");
		XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(voltages);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Function Generator",
                "Time (seconds)",
                "Voltage",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(250, 250));
        panel.add(chartPanel);
        panel.validate();
		return panel;

	}
}