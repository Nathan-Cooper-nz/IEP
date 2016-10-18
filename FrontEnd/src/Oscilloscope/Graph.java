package Oscilloscope;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * The Display consists of the Oscilloscope Graph and all
 * of the features that will be displayed using the graphing
 * libraries
 *
 * @author nztyler
 *
 */
public class Graph extends JPanel {

	private Date startDate;
	private long startTime;

	// voltages to be displayed on the graphs
	private XYSeries ch1Voltages;
	private XYSeries ch2Voltages;
	private XYSeries funcVoltages;

	private OscilloscopePanel oscPanel;	//I may not even need this
	private DisplayThread oscThread;

	public Graph(OscilloscopePanel oscPanel){
		this.oscPanel = oscPanel;

		ch1Voltages = new XYSeries("CH1");
		ch2Voltages = new XYSeries("CH2");
		funcVoltages = new XYSeries("Func");

		resetVoltages();

		XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(ch1Voltages);
        dataset.addSeries(ch2Voltages);
        dataset.addSeries(funcVoltages);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Oscilloscope",
                "Time (seconds)",
                "Voltage",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        chart.removeLegend();

        XYPlot plot = (XYPlot)chart.getPlot();
       // plot.setBackgroundPaint(Color.WHITE);
        chart.setBackgroundPaint(oscPanel.getBackground());
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.black);
        plot.setDomainGridlinePaint(Color.black);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(3.0f));

        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0, 10);
        domain.setTickUnit(new NumberTickUnit(1.0));
        domain.setVerticalTickLabels(false);

        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-10, 10);
        range.setTickUnit(new NumberTickUnit(1.0));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        add(chartPanel);
        validate();
	}

	public void resetVoltages(){
		clear(ch1Voltages);
		clear(ch2Voltages);
		clear(funcVoltages);
	}

	public void clear(XYSeries val) {
		val.clear();
	}

	public DisplayThread getOscilloscopeThread() {
		return oscThread;
	}

	public XYSeries channelOne() {
		return ch1Voltages;
	}

	public XYSeries channelTwo() {
		return ch2Voltages;
	}

	public XYSeries functionGenerator() {
		return funcVoltages;
	}

}
