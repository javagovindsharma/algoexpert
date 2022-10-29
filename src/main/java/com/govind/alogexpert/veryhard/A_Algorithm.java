package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A_Algorithm {

	class Node {
		String id;
		int row;
		int col;
		int value;
		int distanceFromStart;
		int estimatedDistanceToEnd;
		Node cameFrom;

		public Node(int row, int col, int value) {
			this.id = String.valueOf(row) + '-' + String.valueOf(col);
			this.row = row;
			this.col = col;
			this.value = value;
			this.distanceFromStart = Integer.MAX_VALUE;
			this.estimatedDistanceToEnd = Integer.MAX_VALUE;
			this.cameFrom = null;
		}
	}

	class MinHeap {
		List<Node> heap = new ArrayList<Node>();
		Map<String, Integer> nodePositionsInHeap = new HashMap<String, Integer>();

		public MinHeap(List<Node> array) {
			for (int i = 0; i < array.size(); i++) {
				Node node = array.get(i);
				nodePositionsInHeap.put(node.id, i);
			}
			heap = buildHeap(array);
		}

		private List<Node> buildHeap(List<Node> array) {
			int firstParentIdx = (array.size() - 2) / 2;
			for (int currentIdx = firstParentIdx + 1; currentIdx >= 0; currentIdx--) {
				siftDown(currentIdx, array.size() - 1, array);
			}
			return array;
		}

		boolean isEmpty() {
			return heap.size() == 0;
		}

		private void siftDown(int currentIdx, int endIdx, List<Node> array) {
			int childOneIdx = currentIdx*2+1;
			while (childOneIdx <= endIdx) {
				int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;
				int idxToSwap;
				if (childTwoIdx != -1 && array.get(childTwoIdx).estimatedDistanceToEnd < heap
						.get(childOneIdx).estimatedDistanceToEnd) {
					idxToSwap = childTwoIdx;
				} else {
					idxToSwap = childOneIdx;
				}

				if (array.get(idxToSwap).estimatedDistanceToEnd < array.get(currentIdx).estimatedDistanceToEnd) {
					swap(currentIdx, idxToSwap);
					currentIdx = idxToSwap;
					childOneIdx = currentIdx * 2 + 1;
				} else {
					return;
				}
			}
		}

		void siftUp(int currentIdx) {
			int parentIdx = (currentIdx - 1) / 2;
			while (currentIdx > 0
					&& heap.get(currentIdx).estimatedDistanceToEnd < heap.get(parentIdx).estimatedDistanceToEnd) {
				swap(currentIdx, parentIdx);
				currentIdx = parentIdx;
				parentIdx = (currentIdx - 1) / 2;
			}
		}

		Node remove() {
			if (isEmpty()) {
				return null;
			}
			swap(0, heap.size() - 1);
			Node node = heap.get(heap.size() - 1);
			heap.remove(heap.size() - 1);
			nodePositionsInHeap.remove(node.id);
			siftDown(0, heap.size() - 1, heap);
			return node;
		}

		void insert(Node node) {
			heap.add(node);
			nodePositionsInHeap.put(node.id, heap.size() - 1);
			siftUp(heap.size() - 1);
		}

		void swap(int i, int j) {
			nodePositionsInHeap.put(heap.get(i).id, j);
			nodePositionsInHeap.put(heap.get(j).id, i);
			Node temp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, temp);
		}

		boolean containsNode(Node node) {
			return nodePositionsInHeap.containsKey(node.id);
		}

		void update(Node node) {
			siftUp(nodePositionsInHeap.get(node.id));
		}
	};

	public int[][] aStarAlgorithm(int startRow, int startCol, int endRow, int endCol, int[][] graph) {
		List<List<Node>> nodes = initializeNodes(graph);
		Node startNode = nodes.get(startRow).get(startCol);
		Node endNode = nodes.get(endRow).get(endCol);

		startNode.distanceFromStart = 0;
		startNode.estimatedDistanceToEnd = calculateManhattanDistance(startNode, endNode);

		List<Node> nodesToVisitList = new ArrayList<Node>();
		nodesToVisitList.add(startNode);
		MinHeap nodesToVisit = new MinHeap(nodesToVisitList);

		while (!nodesToVisit.isEmpty()) {
			Node currentMinDistanceNode = nodesToVisit.remove();
			if (currentMinDistanceNode == endNode) {
				break;
			}

			List<Node> neighbors = getNeightboringNodes(currentMinDistanceNode, nodes);
			for (Node neighbor : neighbors) {
				if (neighbor.value == 1) {
					continue;
				}
				int tentativeDistanceToNeighbor = currentMinDistanceNode.distanceFromStart + 1;
				if (tentativeDistanceToNeighbor >= neighbor.distanceFromStart) {
					continue;
				}
				neighbor.cameFrom = currentMinDistanceNode;
				neighbor.distanceFromStart = tentativeDistanceToNeighbor;
				neighbor.estimatedDistanceToEnd = tentativeDistanceToNeighbor
						+ calculateManhattanDistance(neighbor, endNode);

				if (!nodesToVisit.containsNode(neighbor)) {
					nodesToVisit.insert(neighbor);
				} else {
					nodesToVisit.update(neighbor);
				}
			}

		}
		return reconstructPath(endNode);
	}


	private List<List<Node>> initializeNodes(int[][] graph) {
		List<List<Node>> nodes=new ArrayList<List<Node>>();
		for(int i=0;i<graph.length;i++) {
			List<Node> nodeList=new ArrayList<Node>();
			nodes.add(nodeList);
			for(int j=0;j<graph[i].length;j++) {
				nodes.get(i).add(new Node(i,j,graph[i][j]));
			}
		}
		return nodes;
	}

	private int calculateManhattanDistance(Node currentNode, Node endNode) {
		int currentRow=currentNode.row;
		int currentCol=currentNode.col;
		int endRow=currentNode.row;
		int endCol=currentNode.col;
		return Math.abs(currentRow- endRow)+Math.abs(currentCol- endCol);
	}

	private List<Node> getNeightboringNodes(Node node, List<List<Node>> nodes) {
		List<Node> neighbors=new ArrayList<>();
		int numRows=nodes.size();
		int numCols=nodes.get(0).size();
		int row=node.row;
		int col=node.col;
		
		if(row<numRows-1) {//DOWN
			neighbors.add(nodes.get(row+1).get(col));
		}
		if(row>0) {//UP
			neighbors.add(nodes.get(row-1).get(col));
		}
		if(col<numCols-1) {//RIGHT
			neighbors.add(nodes.get(row).get(col+1));
		}
		if(col>0) {//LEFT
			neighbors.add(nodes.get(row).get(col-1));
		}
		return neighbors;
	}

	private int[][] reconstructPath(Node endNode) {
		if(endNode.cameFrom==null) {
			return new int[][] {};
		}
		
		Node currentNode=endNode;
		List<List<Integer>> path=new ArrayList<>();
		while(currentNode!=null) {
			List<Integer> nodeData=new ArrayList<>();
			nodeData.add(currentNode.row);
			nodeData.add(currentNode.col);
			path.add(nodeData);
			currentNode=currentNode.cameFrom;
		}
		
		int[][] res=new int[path.size()][2];
		for(int i=0;i<res.length;i++) {
			res[i][0]=path.get(res.length-1-i).get(0);
			res[i][1]=path.get(res.length-1-i).get(1);
		}
		
		return res;
	}

	public static void main(String[] args) {
		int startRow = 0;
		int startCol = 1;
		int endRow = 4;
		int endCol = 3;
		int[][] graph = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 1, 1 },
				{ 0, 0, 0, 0, 0 }, };
		int[][] expected = new int[][] { { 0, 1 }, { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 4, 2 },
				{ 4, 3 } };
		int[][] actual = new A_Algorithm().aStarAlgorithm(startRow, startCol, endRow, endCol, graph);
		assertTrue(expected.length == actual.length);
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				assertTrue(expected[i][j] == actual[i][j]);
			}
		}

	}

}
