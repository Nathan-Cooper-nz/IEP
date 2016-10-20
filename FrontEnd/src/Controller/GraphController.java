package Controller;

import Oscilloscope.Graph;
import org.jfree.data.xy.XYSeries;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nztyler on 18/10/16.
 */
public class GraphController {

    private Graph graph;

    public static final int CHANNEL_ONE = 1;
    public static final int CHANNEL_TWO = 2;
    public static final int FUNCTION_GENERATOR = 3;

    public GraphController(Graph graph) {
        this.graph = graph;
    }

    /**
     * Clears the voltages that are currently being displayed
     */
    public void clear() {
        graph.resetVoltages();
    }

    /**
     *  Sets the values for both channels and function generator
     * @param first
     * @param second
     * @param third
     */
    public void setVoltage(String first, String second, String third) {
        // clear the panel
        clear();

        if (first != null)
            parse(graph.channelOne(), first);
        if (second != null)
            parse(graph.channelTwo(), second);
        if (third != null)
            parse(graph.functionGenerator(), third);
    }

    /**
     * Sets the values for a single channel or function generator
     * @param text the values to be added to the channel
     * @param channel represents the channel which is being set
     */
    public void setChannel(String text, int channel) {
        if (channel == CHANNEL_ONE)  {
            graph.clear(graph.channelOne());
            parse(graph.channelOne(), text);
        } else if (channel == CHANNEL_TWO) {
            graph.clear(graph.channelTwo());
            parse(graph.channelTwo(), text);
        } else if (channel == FUNCTION_GENERATOR) {
            graph.clear(graph.functionGenerator());
            parse(graph.functionGenerator(), text);
        } else {
            throw new Error("Input is not either channel1, channel2 or function generator");
        }
    }

    /**
     * The parser method that assigns a single channel/function generator
     * its voltages and spreads its values across the graph
     * @param values The collection storing the voltage, time pairs
     * @param text The entire string to be parsed
     */
    public void parse(XYSeries values, String text) {
        List<String> strValues = Arrays.asList(text.split(","));
        for (int index = 0; index < strValues.size(); index ++) {
            double value = Double.parseDouble(strValues.get(index));
            values.add((double)index / (double) strValues.size() * 10.0 , value);
        }
    }
}
