/**
 * Commented out due to migration
 */

//package GUI;
//
//import java.util.Random;
//
//import org.graphstream.algorithm.measure.ChartMeasure.PlotException;
//import org.graphstream.algorithm.measure.ChartSeries1DMeasure;
//import org.graphstream.graph.*;
//import org.graphstream.graph.implementations.*;
//
//public class GraphStreamTesting {
//	
//	private static SingleGraph triangleGraph(){
//		SingleGraph graph = new SingleGraph("Graph1");
//		graph.addNode("1");
//		graph.addNode("2");
//		graph.addNode("3");
//		graph.addEdge("12","1","2");
//		graph.addEdge("23","2","3");
//		graph.addEdge("31","3","1");
//		return graph;
//	}
//	
//	private static ChartSeries1DMeasure lineGraph(){
//		ChartSeries1DMeasure graph = new ChartSeries1DMeasure("Graph2");
//		Random r = new Random();
//		
//		for (int i=0;i<100;i++){
//			graph.addValue(r.nextDouble()*10);
//		}
//		try {
//			graph.plot();
//		} catch (PlotException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return graph;
//	}
//	
//	public static void main(String[] args ){
//		//SingleGraph graph = triangleGraph();	
//		ChartSeries1DMeasure graph = lineGraph();
//	}
//
//}
