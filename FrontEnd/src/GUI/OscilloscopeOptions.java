package GUI;

import GUI.CursorTab;
import GUI.MeasureTab;
import GUI.TriggerTab;

import Network.Network;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * The Options that should be used to changes the values
 * for the settings on the Oscilloscope. There will be a 
 * lot of buttons, so tabs are most likely the best way to 
 * implement this
 * 
 * @author nztyler
 *
 */
public class OscilloscopeOptions extends JTabbedPane {
	
	private ChannelTab channel;
	private TriggerTab trigger;
	private CursorTab cursor;
	private MeasureTab measure;
	
    public OscilloscopeOptions(Network net){
    	channel = new ChannelTab(net);
    	trigger = new TriggerTab();
    	cursor = new CursorTab();
    	measure = new MeasureTab();

    	addTab("Channel", channel);
    	addTab("Trigger", trigger);
    	addTab("Cursor", cursor);
    	addTab("Measure", measure);
    	
    	Border border = LineBorder.createGrayLineBorder();
        setBorder(border);
    }

    // Getter so the measure controller can be linked to this tab
    public MeasureTab getMeasureTab() {
		return measure;
	}
}