package Controller;

import GUI.MeasureDisplay;
import GUI.MeasureTab;

/**
 * Created by nztyler on 18/10/16.
 */
public class MeasureController {

    private double[] dataValues;

    private MeasureDisplay display;
    private MeasureTab tab;

    private MeasureController(MeasureDisplay display, MeasureTab tab) {
        this.display = display;
        this.tab = tab;

        dataValues = new double[5];

        // for testing purposes
        for (int i = 0; i < 5; i++) {
            dataValues[i] = i;
        }
    }

    /**
     * This should update both the measure panel and measure tab
     */
    public void update() {
        display.update(dataValues);
        tab.update(dataValues);
    }
}
