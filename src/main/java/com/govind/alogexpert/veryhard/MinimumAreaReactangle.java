package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class MinimumAreaReactangle {
	// solution 1

	public int minimumAreaRectangle(int[][] points) {
		HashMap<Integer, int[]> columns = initialeColumns(points);
		int minimumAreaFound = Integer.MAX_VALUE;
		HashMap<String, Integer> edgesParallelToYAxis = new HashMap<String, Integer>();
		ArrayList<Integer> sortedColumns = new ArrayList<Integer>(columns.keySet());
		Collections.sort(sortedColumns);
		for (Integer x : sortedColumns) {
			int[] yvaluesInCurrentColumn = columns.get(x);
			Arrays.sort(yvaluesInCurrentColumn);

			for (int currentIdx = 0; currentIdx < yvaluesInCurrentColumn.length; currentIdx++) {
				int y2 = yvaluesInCurrentColumn[currentIdx];
				for (int previousIdx = 0; previousIdx < currentIdx; previousIdx++) {
					int y1 = yvaluesInCurrentColumn[previousIdx];
					String pointString = String.valueOf(y1) + ":" + String.valueOf(y2);

					if (edgesParallelToYAxis.containsKey(pointString)) {
						int currentArea = (x - edgesParallelToYAxis.get(pointString)) * (y2 - y1);
						minimumAreaFound = Math.min(minimumAreaFound, currentArea);
					}
					edgesParallelToYAxis.put(pointString, x);
				}
			}
		}
		return (minimumAreaFound != Integer.MAX_VALUE) ? minimumAreaFound : 0;
	}

	private HashMap<Integer, int[]> initialeColumns(int[][] points) {
		HashMap<Integer, int[]> columns = new HashMap<Integer, int[]>();

		for (int[] point : points) {
			int x = point[0];
			int y = point[1];

			if (!columns.containsKey(x)) {
				columns.put(x, new int[] {});
			}

			int[] column = columns.get(x);
			int[] newColumn = new int[column.length + 1];

			for (int i = 0; i < column.length; i++) {
				newColumn[i] = column[i];
			}
			newColumn[column.length] = y;
			columns.put(x, newColumn);
		}
		return columns;
	}
	
	
	// solution 2
	 public int minimumAreaRectangle2(int[][] points) {
			HashSet<String> pointSet = createPointSet(points);
			int minimumAreaFound = Integer.MAX_VALUE;

			for (int currentIdx = 0; currentIdx < points.length; currentIdx++) {
				int p2x = points[currentIdx][0];
				int p2y = points[currentIdx][1];
				for (int previousIdx = 0; previousIdx < currentIdx; previousIdx++) {
					int p1x = points[previousIdx][0];
					int p1y = points[previousIdx][1];
					boolean pointsShareValue = p1x == p2x || p1y == p2y;

					if (pointsShareValue)
						continue;

					boolean point1OnOppositeDiagonalExits = pointSet.contains(convertPointToString(p1x, p2y));
					boolean point2OnOppositeDiagonalExits = pointSet.contains(convertPointToString(p2x, p1y));
					boolean oppositeDiagonalExits = point1OnOppositeDiagonalExits && point2OnOppositeDiagonalExits;

					if (oppositeDiagonalExits) {
						int currentArea = Math.abs(p2x - p1x) * Math.abs(p2y - p1y);
						minimumAreaFound = Math.min(minimumAreaFound, currentArea);
					}
				}
			}
			return (minimumAreaFound != Integer.MAX_VALUE) ? minimumAreaFound : 0;
		}

		private String convertPointToString(int x, int y) {
			return String.valueOf(x) + ":" + String.valueOf(y);
		}

		private HashSet<String> createPointSet(int[][] points) {
			HashSet<String> pointSet = new HashSet<String>();
			for (int[] point : points) {
				int x = point[0];
				int y = point[1];
				String pointString = convertPointToString(x, y);
				pointSet.add(pointString);
			}
			return pointSet;
		}

	public static void main(String[] args) {
		int[][] input = new int[][] { { 1, 5 }, { 5, 1 }, { 4, 2 }, { 2, 4 }, { 2, 2 }, { 1, 2 }, { 4, 5 }, { 2, 5 },
				{ -1, -2 } };
		int expected = 3;
		int actual = new MinimumAreaReactangle().minimumAreaRectangle(input);
		assertTrue(expected == actual);

	}

}
