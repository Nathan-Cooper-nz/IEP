

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.FunctionDisplay;
import GUI.FunctionOptions;
import Oscilloscope.OscilloscopeOptions;
import Oscilloscope.OscilloscopePanel;

public class IEPframe extends JFrame {

    private FunctionDisplay fGenDisplay;
    private FunctionOptions fGenOptions;
    private OscilloscopePanel oscPanel;
    private OscilloscopeOptions oscOptions;

    public IEPframe(){
    	setTitle("Integrated Electronics Platform");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Trying to figure out how to make the layout nicer
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        
        //Create the displays
        oscPanel = new OscilloscopePanel();
        oscOptions = new OscilloscopeOptions();
        fGenDisplay = new FunctionDisplay();
        fGenOptions = new FunctionOptions();
        //Add displays to the frame
        addComp(panel, oscPanel, 0, 0, 1, 1, 0.65, 0.7);
        addComp(panel, oscOptions, 0, 1, 1, 1, 0.65, 0.3);
        addComp(panel, fGenOptions, 1, 0, 1, 1, 0.35, 0.7);
        addComp(panel, fGenDisplay, 1, 1, 1, 1, 0.35, 0.3);
        setContentPane(panel);
        
        //Display the window.
        setSize(1200,700);
        //pack();
        setVisible(true);
    }
    
    public OscilloscopePanel getOscilloscopePanel(){
    	return oscPanel;
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
    public static void addComp(JPanel panel, JComponent component,
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