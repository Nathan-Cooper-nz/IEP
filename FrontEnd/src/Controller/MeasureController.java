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

    public MeasureController(MeasureDisplay display, MeasureTab tab) {
        this.display = display;
        this.tab = tab;

        dataValues = new double[5];

        // for testing purposes
        for (int i = 0; i < 5; i++) {
            dataValues[i] = i;
        }
    }

    /**
     * This is used to change the measurements for the tabs and
     * the measure display
     * @param value The amount that this is being changed to
     * @param index The index that the value is replacing
     */
    public void alter(double value, int index) {
        if (index < 0 || index >= 5)
            throw new Error("Index out of bound");
        dataValues[index] = value;
        update();
    }

    /**
     * This should update both the measure panel and measure tab
     */
    public void update() {
        display.update(dataValues);
        tab.update(dataValues);
    }
}
