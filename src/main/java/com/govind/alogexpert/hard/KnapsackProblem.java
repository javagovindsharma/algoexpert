package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {

	public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
		int[][] knapsackValues = new int[items.length + 1][capacity + 1];
		for (int i = 1; i < items.length + 1; i++) {
			int currentWeight = items[i - 1][1];
			int currentValue = items[i - 1][0];

			for (int c = 0; c < capacity + 1; c++) {
				if (currentWeight > c) {
					knapsackValues[i][c] = knapsackValues[i - 1][c];
				} else {
					knapsackValues[i][c] = Math.max(knapsackValues[i - 1][c],
							knapsackValues[i - 1][c - currentWeight] + currentValue);
				}
			}
		}
		return getKnapsackItems(knapsackValues, items, knapsackValues[items.length][capacity]);
	}

	private static List<List<Integer>> getKnapsackItems(int[][] knapsackValues, int[][] items, int weight) {
		List<List<Integer>> sequence = new ArrayList<List<Integer>>();
		List<Integer> totalWeigth = new ArrayList<Integer>();

		totalWeigth.add(weight);
		sequence.add(totalWeigth);
		sequence.add(new ArrayList<Integer>());
		int i = knapsackValues.length - 1;
		int c = knapsackValues[0].length - 1;
		while (i > 0) {
			if (knapsackValues[i][c] == knapsackValues[i - 1][c]) {
				i--;
			} else {
				sequence.get(1).add(0, i - 1);
				c -= items[i - 1][1];
				i--;
			}
			if (c == 0) {
				break;
			}
		}

		return sequence;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
