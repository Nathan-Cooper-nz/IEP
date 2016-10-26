package Component;

import GUI.ChannelTab;

/**
 * Created by nztyler on 24/10/16.
 */
public class Channel {

    private String name;

    private double pos;	//dpad
    private int volt;	//dpad
    private Coupling coup;
    private Probe probe;

    public Channel(String name) {
        this.name = name;
        // set default values
    }

    public String getName() {
        return name;
    }

    public void setPosition(double pos) {
        this.pos = pos;
    }

    public double GetPosition() {
        return pos;
    }

    public void setVoltage(int volt) {
        this.volt = volt;
    }

    public int getVoltage() {
        return volt;
    }

    public void setCoupling(Coupling coup) {
        this.coup = coup;
    }

    public Coupling getCoupling() {
        return coup;
    }

    public void setProbe(Probe probe) {
        this.probe = probe;
    }

    public Probe getProbe() {
        return probe;
    }

}
