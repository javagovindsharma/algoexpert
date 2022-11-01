package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

public class DetectArbitrage {

	public boolean detectArbitrage(ArrayList<ArrayList<Double>> exchangeRates) {
	    ArrayList<ArrayList<Double>> logExchangeRates=convertToLogMatrix(exchangeRates);
	    return foundnegativeWeightCycle(logExchangeRates,0);
	  }
	
	private boolean foundnegativeWeightCycle(ArrayList<ArrayList<Double>> graph, int start) {
		double[] distancesFromStart=new double[graph.size()];
		Arrays.fill(distancesFromStart, Double.MAX_VALUE);
		distancesFromStart[start]=0;
		for(int unused=0;unused<graph.size();unused++){
			if(!relaxEdgesAndUpdateDistances(graph,distancesFromStart)) {
				return false;
			}
		}
		return relaxEdgesAndUpdateDistances(graph,distancesFromStart);
	}
	private boolean relaxEdgesAndUpdateDistances(ArrayList<ArrayList<Double>> graph, double[] distances) {
		boolean updated=false;
		for(int sourceIdx=0;sourceIdx<graph.size();sourceIdx++) {
			ArrayList<Double> edges=graph.get(sourceIdx);
			for(int destinationIdx=0;destinationIdx<edges.size();destinationIdx++) {
				double edgeWeight=edges.get(destinationIdx);
				double newDistanceToDestination=distances[sourceIdx]+edgeWeight;
				if(newDistanceToDestination<distances[destinationIdx]) {
					updated=true;
					distances[destinationIdx]=newDistanceToDestination;
				}
			}
		}
		return updated;
	}
	
	private ArrayList<ArrayList<Double>> convertToLogMatrix(ArrayList<ArrayList<Double>> matrix) {
		ArrayList<ArrayList<Double>> newMatrix=new ArrayList<ArrayList<Double>>();
		for(int row=0;row<matrix.size();row++) {
			ArrayList<Double> rates=matrix.get(row);
			newMatrix.add(new ArrayList<Double>());
			for(Double rate:rates) {
				newMatrix.get(row).add(-Math.log10(rate));
			}
		}
		return newMatrix;
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> input = new ArrayList<ArrayList<Double>>();
	    input.add(new ArrayList<Double>(Arrays.asList(1.0, 0.8631, 0.5903)));
	    input.add(new ArrayList<Double>(Arrays.asList(1.1586, 1.0, 0.6849)));
	    input.add(new ArrayList<Double>(Arrays.asList(1.6939, 1.46, 1.0)));
	    boolean expected = true;
	    boolean actual = new DetectArbitrage().detectArbitrage(input);
	    assertTrue(expected == actual);

	}

}
