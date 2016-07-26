package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class OscilloscopeDisplay extends JPanel{

	private boolean channelOneOn = false;
	private boolean channelTwoOn = false;
	
    public OscilloscopeDisplay(){
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //add the oscilloscope display and buttons
        addComp(this, getDisplayPanel(), 0, 0, 1, 1, 1, 0.9);
        addComp(this, getButtonPanel(), 1, 0, 1, 1, 1, 0.1);
    }
    
    public JPanel getDisplayPanel(){
    	JPanel panel = new JPanel();
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
        addComp(panel, chartPanel, 0, 0, 1, 1, 0.9, 1);
        panel.validate();
		return panel;
    	
    }
    
    public JPanel getButtonPanel(){
    	JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton saveButton = new JButton("Save");	//screenshot
        saveButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Save button pressed");
        		channelOneOn = false;
        		channelTwoOn = false;
        	}
        });
        ButtonGroup channel = new ButtonGroup();
        JButton channelOne = new JButton("CH1");
        channelOne.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("First channel selected");
        		channelOneOn = true;
        		channelTwoOn = false;
        		
        	}
        });
        JButton channelTwo = new JButton("CH2");
        channelTwo.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Second channel selected");
        		channelOneOn = false;
        		channelTwoOn = true;
        	}
        });
        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Pause button pressed");
        	}
        });

        channel.add(channelOne);
        channel.add(channelTwo);
  
        buttonPanel.add(channelOne);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPanel.add(channelTwo);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,20)));
        buttonPanel.add(pause);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPanel.add(saveButton);
        return buttonPanel;
    }
    
    public JPanel getGraph(){
    	JPanel panel = new JPanel();
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
        panel.add(chartPanel);
        panel.validate();
		return panel;
    }
    
    /**
     * This is a helper method which is only used by this class to add
     * components to a panel
     * @param panel The panel that the component is being added to
     * @param component The component to be added
     * @param x Specifies the GridBagConstraints.gridx
     * @param y Specifies the GridBagConstraints.gridy
     * @param width Specifies the GridBagConstraints.gridwidth
     * @param height Specifies the GridBagConstraints.gridheight
     * @param weightX specifies the GridBagConstraints.weightx
     * @param weightY specifies the GridBagConstraints.weighty
     */
    private void addComp(JPanel panel, JComponent component,
    		int x, int y, int width, int height, double weightX, double weightY){
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = x;
    	gbc.gridy = y;
    	gbc.gridheight = height;
    	gbc.gridwidth = width;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.weightx = weightX;
    	gbc.weighty = weightY;
    	panel.add(component, gbc);	
    }
    
}
