package Oscilloscope;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;

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
	
	private JPanel channel;
	private JPanel trigger;
	private JPanel cursor;
	private JPanel measure;
	
    public OscilloscopeOptions(){    
    	channel = new ChannelTab();
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
    
    /**
     * This just makes it clearer that there should always be a specific
     * type of coupling
     * @author nztyler
     *
     */
    public enum Coupling {
    	AC, DC
    }
}