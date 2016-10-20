package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Oscilloscope.Graph;

import Network.Network;

public class IEPframe extends JFrame {

    private MeasureDisplay oscMeasure;
    private FunctionOptions fGenOptions;
    private Graph graph;
    private OscilloscopeOptions oscOptions;

    private Network network;

    public IEPframe(){
    	setTitle("Integrated Electronics Platform");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Trying to figure out how to make the layout nicer
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        // Create the network

        network = new Network();

        // Create the displays
        graph = new Graph(this);
        oscOptions = new OscilloscopeOptions();

        oscMeasure = new MeasureDisplay();
        fGenOptions = new FunctionOptions(network);

        //Add displays to the frame
        addComp(panel, graph, 0, 0, 1, 1, 0.65, 0.7);
        addComp(panel, oscOptions, 0, 1, 1, 1, 0.65, 0.3);
        addComp(panel, oscMeasure, 1, 0, 1, 1, 0.35, 0.3);
        addComp(panel, fGenOptions, 1, 1, 1, 1, 0.35, 0.7);
        setContentPane(panel);
        
        //Display the window.
        setSize(1200,700);
        //pack();
        setVisible(true);
    }

    // These are the two getters needed for the the measure controller
    public MeasureDisplay getMeasureDisplay() { return oscMeasure; }
    public MeasureTab getMeasureTab() { return oscOptions.getMeasureTab(); }

    // This is the getter needed for the graph controller
    public Graph getGraph() { return graph; }

    // This is for getting the network for the thread
    public Network getNetwork() { return network; }
    
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
