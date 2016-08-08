package Oscilloscope;

import java.awt.Dimension;
import java.util.Date;

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
public class OscilloscopeDisplay extends JPanel {
	
	private Date startDate;
	private long startTime;
	private XYSeries voltages;

	private OscilloscopePanel oscPanel;	//I may not even need this
	private OscilloscopeThread oscThread;
	
	public OscilloscopeDisplay(OscilloscopePanel oscPanel){
		this.oscPanel = oscPanel;
		oscThread = new OscilloscopeThread(this);
		
		startDate = new Date();
		startTime = startDate.getTime();
		
		voltages = new XYSeries("Voltages");
		//Set the initial time/voltages to 0,0
		voltages.add(0, 0);
		
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
        
        XYPlot plot = (XYPlot)chart.getPlot();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0, 10);
        domain.setTickUnit(new NumberTickUnit(1.0));
        domain.setVerticalTickLabels(true);
        
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-10, 10);
        range.setTickUnit(new NumberTickUnit(1.0));
        
        plot.setRenderer(renderer);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        add(chartPanel);
        validate();
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
    void setVoltage(double voltage) {
    	
    	Date currentDate = new Date();
    	double currentTime = (currentDate.getTime() - startTime) / 1000.0;
        
        if (currentTime > 10.0) {
        	
        	startTime = currentDate.getTime();
        	currentTime = 0.0;
        	
        	Runnable clearData = new Runnable() {
                public void run() {
                	voltages.clear();
                }
            };        	
        	
        	SwingUtilities.invokeLater(clearData);
        }
        
        voltages.add(currentTime, voltage);
    }
}
