package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Collections;

public class SunsetView {
	 public ArrayList<Integer> sunsetViews(int[] buildings, String direction) {
			ArrayList<Integer> candidateBuildings = new ArrayList<Integer>();
			int startIdx = buildings.length - 1;
			int step = -1;
			if (direction.equals("EAST")) {
				startIdx = 0;
				step = 1;
			}

			int idx = startIdx;
			while (idx >= 0 && idx < buildings.length) {
				int buildingHeight = buildings[idx];
				while (candidateBuildings.size() > 0
						&& buildings[candidateBuildings.get(candidateBuildings.size() - 1)] <= buildingHeight) {
					candidateBuildings.remove(candidateBuildings.size() - 1);
				}
				candidateBuildings.add(idx);
				idx += step;
	          }
				if (direction.equals("WEST")) {
					Collections.reverse(candidateBuildings);
				}

			return candidateBuildings;
		}

	public ArrayList<Integer> sunsetViews1(int[] buildings, String direction) {
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		int max = 0;
		if (direction.equals("EAST")) {
			for (int i = buildings.length - 1; i >= 0; i--) {
				if (buildings[i] > max) {
					resultList.add(0, i);
					max = buildings[i];
				}
			}
		} else if (direction.equals("WEST")) {
			for (int i = 0; i < buildings.length; i++) {
				if (buildings[i] > max) {
					resultList.add(i);
					max = buildings[i];
				}
			}
		}
		return resultList;
	}

	public static void main(String[] args) {
		int[] buildings = new int[] { 3, 5, 4, 4, 3, 1, 3, 2 };
		String direction = "EAST";
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(3);
		expected.add(6);
		expected.add(7);
		ArrayList<Integer> actual = new SunsetView().sunsetViews(buildings, direction);
		System.out.println(actual.toString());
		System.out.println(expected.equals(actual));

	}

}
