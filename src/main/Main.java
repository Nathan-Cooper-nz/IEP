package main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Main {
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	//Something to make the GUI look nicer
            	try { 
            	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
            	new IEPframe();
            }
        });
    }
}
