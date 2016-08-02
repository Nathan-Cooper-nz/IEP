package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class OscilloscopeDisplay extends JPanel {
	
	public OscilloscopeDisplay(){
		XYSeries voltages = new XYSeries("Voltages");
		XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(voltages);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Oscilloscope",
                "Time (seconds)",
                "Voltage",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        chartPanel.validate();
        add(chartPanel);
	}
}
