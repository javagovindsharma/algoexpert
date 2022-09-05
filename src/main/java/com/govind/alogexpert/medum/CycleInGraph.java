package com.govind.alogexpert.medum;

import java.util.Arrays;

public class CycleInGraph {

	public boolean cycleInGraph(int[][] edges) {
		int numberOfNodes = edges.length;
		boolean[] visited = new boolean[numberOfNodes];
		boolean[] currentlyInStack = new boolean[numberOfNodes];
		Arrays.fill(visited, false);
		Arrays.fill(currentlyInStack, false);

		for (int node = 0; node < numberOfNodes; node++) {
			if (visited[node]) {
				continue;
			}

			boolean containsCycle = isNodeInCycle(node, edges, visited, currentlyInStack);
            if(containsCycle) {
            	return true;
            }
		}

		return false;
	}

	private boolean isNodeInCycle(int node, int[][] edges, boolean[] visited, boolean[] currentlyInStack) {
		visited[node]=true;
		currentlyInStack[node]=true;
		
		boolean containsCycle=false;
		int[] neighbors=edges[node];
		for(int neighbor:neighbors) {
			if(!visited[neighbor]) {
				containsCycle=isNodeInCycle(neighbor, edges, visited, currentlyInStack);
			}
			if(containsCycle) {
				 return true;
			}else if(currentlyInStack[neighbor]){
				return true;
			}
		}
		currentlyInStack[node]=false;
		return false;
	}

	// solution 1
	public int WHITE = 0;
	public int GREY = 1;
	public int BLACK = 3;

	public boolean cycleInGraph1(int[][] edges) {
		int numberOfNodes = edges.length;
		int[] colors = new int[numberOfNodes];
		Arrays.fill(colors, WHITE);

		for (int node = 0; node < numberOfNodes; node++) {
			if (colors[node] != WHITE)
				continue;

			boolean containsCycle = traversalAndColorNodes(node, edges, colors);

			if (containsCycle)
				return true;
		}
		return false;
	}

	private boolean traversalAndColorNodes(int node, int[][] edges, int[] colors) {
		colors[node] = GREY;
		int[] neighbors = edges[node];

		for (int neighbor : neighbors) {
			int neighborColor = colors[neighbor];

			if (neighborColor == GREY) {
				return true;
			}
			if (neighborColor == BLACK) {
				continue;
			}

			boolean containsCycle = traversalAndColorNodes(neighbor, edges, colors);
			if (containsCycle) {
				return true;
			}
		}
		colors[node] = BLACK;
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
