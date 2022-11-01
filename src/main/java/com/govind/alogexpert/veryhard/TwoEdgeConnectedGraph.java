package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class TwoEdgeConnectedGraph {

	public boolean twoEdgeConnectedGraph(int[][] edges) {
		if(edges.length==0) return true;
		
		int[] arrivalTimes=new int[edges.length];
		Arrays.fill(arrivalTimes,-1);
		int startVertex=0;
		if(getMinimumArrivalTimeOfAncestors(startVertex,-1,0,arrivalTimes,edges)==-1) {
			return false;
		}
		
		return areAllVerticesVisited(arrivalTimes);
	}

	private int getMinimumArrivalTimeOfAncestors(int currentVertex, int parent, int currentTime, int[] arrivalTimes, int[][] edges) {
		arrivalTimes[currentVertex]=currentTime;
		int minimumArrivalTime=currentTime;
		
		for(int destination:edges[currentVertex]) {
			if(arrivalTimes[destination]==-1) {
				minimumArrivalTime=Math.min(minimumArrivalTime, getMinimumArrivalTimeOfAncestors(destination, currentVertex, currentTime+1, arrivalTimes, edges));
			}else if(destination!=parent) {
				minimumArrivalTime=Math.min(minimumArrivalTime, arrivalTimes[destination]);
			}
		}
		if(minimumArrivalTime==currentTime && parent!=-1) {
			return -1;
		}
		return minimumArrivalTime;
	}

	private boolean areAllVerticesVisited(int[] arrivalTimes) {
		 for(int time:arrivalTimes) {
			 if(time==-1) {
				 return false;
			 }
		 }
		 return true;
	}

	

	public static void main(String[] args) {
		int[][] input = new int[][] {{1, 2, 5}, {0, 2}, {0, 1, 3}, {2, 4, 5}, {3, 5}, {0, 3, 4}};
	    boolean expected = true;
	    boolean actual = new TwoEdgeConnectedGraph().twoEdgeConnectedGraph(input);
	    assertTrue(expected == actual);

	}

}
