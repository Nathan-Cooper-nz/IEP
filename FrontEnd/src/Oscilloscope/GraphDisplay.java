package Oscilloscope;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
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
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
public class GraphDisplay extends JPanel {

	private Date startDate;
	private long startTime;
	
	// voltages to be displayed on the graphs
	private XYSeries ch1Voltages;
	private XYSeries ch2Voltages;
	private XYSeries funcVoltages;

	private OscilloscopePanel oscPanel;	//I may not even need this
	private OscilloscopeThread oscThread;

	public GraphDisplay(OscilloscopePanel oscPanel){
		this.oscPanel = oscPanel;
		oscThread = new OscilloscopeThread(this);

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

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);

        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0, 10);
        domain.setTickUnit(new NumberTickUnit(1.0));
        domain.setVerticalTickLabels(false);

        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-10, 10);
        range.setTickUnit(new NumberTickUnit(1.0));

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        add(chartPanel);
        validate();
	}
	
	public void resetVoltages(){
		ch1Voltages.clear();
		ch2Voltages.clear();
		funcVoltages.clear();
		
		ch1Voltages.add(0, 0);
		ch2Voltages.add(0, 0);
		funcVoltages.add(0, 0);
	}

	public OscilloscopeThread getOscilloscopeThread(){
		return oscThread;
	}

	/**
     * This sets the voltage value at a particular point in term on the oscilloscope.
     * If it sweeps over the end then it resets and goes back to the start.
     *
     * @param voltage Value to set on the oscilloscope.
     */
    public void addVoltage(double voltage) {

    	Date currentDate = new Date();
    	double currentTime = (currentDate.getTime() - startTime) / 1000.0;

        if (currentTime > 10.0) {
        	startTime = currentDate.getTime();
        	currentTime = 0.0;

        	Runnable clearData = new Runnable() {
                public void run() {
                	ch1Voltages.clear();
                }
            };
        	SwingUtilities.invokeLater(clearData);
        }
        
        ch1Voltages.add(currentTime, voltage);
    }
    
    /**
     * The format for the string atm is 1,2,3,4,5,6,7,8.....
     * @param data the data to be parsed and added to voltages
     */
    public void setVoltage(String first, String second, String third) {
    	
    	resetVoltages();
    	
    	//remove out any initial characters if formatting is wrong
    	
    	if (first != null){
    	List<String> strValues = Arrays.asList(first.split(","));
	    	for (int index = 0; index < strValues.size(); index ++) {
	    		System.out.println(strValues.get(index));
	    		double value = Double.parseDouble(strValues.get(index));
	    		ch1Voltages.add((double)index / (double) strValues.size() * 10.0 , value);
	    	}
    	}
    	if (second != null){
        	List<String> strValues = Arrays.asList(second.split(","));
    	    for (int index = 0; index < strValues.size(); index ++) {
    	    	double value = Double.parseDouble(strValues.get(index));
    	    	ch2Voltages.add((double)index / (double) strValues.size() * 10.0 , value);
    	    }
        }
    	if (second != null){
        	List<String> strValues = Arrays.asList(third.split(","));
    	    	for (int index = 0; index < strValues.size(); index ++) {
    	    		double value = Double.parseDouble(strValues.get(index));
    	    		funcVoltages.add((double)index / (double) strValues.size() * 10.0 , value);
    	    	}
        	}
    }

}
