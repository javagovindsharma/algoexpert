package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jcp.xml.dsig.internal.dom.Utils;

public class ApartmentHunting {

	// solution 1
	public static int apartmentHunting1(List<Map<String, Boolean>> blocks, String[] reqs) {
		int[] maxDistancesAtBlocks = new int[blocks.size()];
		Arrays.fill(maxDistancesAtBlocks, Integer.MIN_VALUE);

		for (int i = 0; i < blocks.size(); i++) {
			for (String req : reqs) {
				int closestReqDistance = Integer.MAX_VALUE;
				for (int j = 0; j < blocks.size(); j++) {
					if (blocks.get(j).get(req)) {
						closestReqDistance = Math.min(closestReqDistance, distanceBetween(i, j));
					}
				}
				maxDistancesAtBlocks[i] = Math.max(maxDistancesAtBlocks[i], closestReqDistance);
			}
		}
		return getIdxAtMinValue(maxDistancesAtBlocks);
	}

	private static int distanceBetween1(int a, int b) {
		return Math.abs(a - b);
	}

	private static int getIdxAtMinValue1(int[] array) {
		int idxAtMinValue = 0;
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			int currentValue = array[i];
			if (currentValue < minValue) {
				minValue = currentValue;
				idxAtMinValue = i;
			}
		}
		return idxAtMinValue;
	}

	// solution 2
	public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
		int[][] minDistancesFromBlocks = new int[reqs.length][];
		for (int i = 0; i < reqs.length; i++) {
			minDistancesFromBlocks[i] = getMinDistances(blocks, reqs[i]);
		}
		int[] maxDistancesAtBlocks = getMaxDistancesAtBlocks(blocks, minDistancesFromBlocks);
		return getIdxAtMinValue(maxDistancesAtBlocks);
	}

	private static int[] getMinDistances(List<Map<String, Boolean>> blocks, String reqs) {
		int[] minDistances = new int[blocks.size()];
		int closestReqIdx = Integer.MAX_VALUE;
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).get(reqs))
				closestReqIdx = i;
			minDistances[i] = distanceBetween(i, closestReqIdx);
		}
		for (int i = blocks.size() - 1; i >= 0; i--) {
			if (blocks.get(i).get(reqs))
				closestReqIdx = i;
			minDistances[i] = Math.min(minDistances[i], distanceBetween(i, closestReqIdx));
		}
		return minDistances;
	}

	private static int[] getMaxDistancesAtBlocks(List<Map<String, Boolean>> blocks, int[][] minDistancesFromBlocks) {
		int[] maxDistancesAtBlocks = new int[blocks.size()];
		for (int i = 0; i < blocks.size(); i++) {
			int[] minDistancesAtBlocks = new int[minDistancesFromBlocks.length];
			for (int j = 0; j < minDistancesFromBlocks.length; j++) {
				minDistancesAtBlocks[j] = minDistancesFromBlocks[j][i];
			}
			maxDistancesAtBlocks[i] = arrayMax(minDistancesAtBlocks);
		}
		return maxDistancesAtBlocks;
	}

	private static int distanceBetween(int a, int b) {
		return Math.abs(a - b);
	}

	private static int arrayMax(int[] array) {
		int max = array[0];
		for (int a : array) {
			if (a > max) {
				max = a;
			}
		}
		return max;
	}

	private static int getIdxAtMinValue(int[] array) {
		int idxAtMinValue = 0;
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			int currentValue = array[i];
			if (currentValue < minValue) {
				minValue = currentValue;
				idxAtMinValue = i;
			}
		}
		return idxAtMinValue;
	}

	public static void main(String[] args) {
		List<Map<String, Boolean>> blocks = new ArrayList<Map<String, Boolean>>();

		blocks.add(0, new HashMap<String, Boolean>());
		blocks.get(0).put("gym", false);
		blocks.get(0).put("school", true);
		blocks.get(0).put("store", false);

		blocks.add(1, new HashMap<String, Boolean>());
		blocks.get(1).put("gym", true);
		blocks.get(1).put("school", false);
		blocks.get(1).put("store", false);

		blocks.add(2, new HashMap<String, Boolean>());
		blocks.get(2).put("gym", true);
		blocks.get(2).put("school", true);
		blocks.get(2).put("store", false);

		blocks.add(3, new HashMap<String, Boolean>());
		blocks.get(3).put("gym", false);
		blocks.get(3).put("school", true);
		blocks.get(3).put("store", false);

		blocks.add(4, new HashMap<String, Boolean>());
		blocks.get(4).put("gym", false);
		blocks.get(4).put("school", true);
		blocks.get(4).put("store", true);

		String[] reqs = new String[] { "gym", "school", "store" };
		assertTrue(ApartmentHunting.apartmentHunting(blocks, reqs) == 3);

	}

}
