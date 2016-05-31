package main;

import javax.swing.*;
import java.awt.*;

public class IEPframe extends JFrame {

    private FunctionDisplay fGenDisplay;
    private FunctionOptions fGenOptions;
    private OscilloscopeDisplay oscDisplay;
    private OscilloscopeOptions oscOptions;

    private JFrame frame;

    public IEPframe(){
        //Create and set up the window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add a menu bar
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("JMenuBar");
        bar.add(menu);
        frame.setJMenuBar(bar);
        //Trying to figure out how to make the layout nicer
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(2,2);
        layout.setHgap(0);
        layout.setVgap(0);
        panel.setLayout(layout);
        //Create the displays
        oscDisplay = new OscilloscopeDisplay();
        oscOptions = new OscilloscopeOptions();
        fGenDisplay = new FunctionDisplay();
        fGenOptions = new FunctionOptions();
        //Add displays to the frame
        panel.add(oscDisplay);
        panel.add(fGenOptions);
        panel.add(oscOptions);
        panel.add(fGenDisplay);
        frame.add(panel);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}