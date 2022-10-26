package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

public class MaxProfitWithKTransactions {

	// solution 2
	public static int maxProfitWithKTransactions(int[] prices, int k) {
		if (prices.length == 0) {
			return 0;
		}

		int[] evenProfits = new int[prices.length];
		int[] oddProfits = new int[prices.length];
		int[] currentProfits;
		int[] previousProfits;

		for (int t = 1; t < k + 1; t++) {
			int maxThurFar = Integer.MIN_VALUE;
			if (t % 2 == 1) {
				currentProfits = oddProfits;
				previousProfits = evenProfits;
			} else {
				currentProfits = evenProfits;
				previousProfits = oddProfits;
			}

			for (int d = 1; d < prices.length; d++) {
				maxThurFar = Math.max(maxThurFar, previousProfits[d - 1] - prices[d - 1]);
				currentProfits[d] = Math.max(currentProfits[d - 1], maxThurFar + prices[d]);
			}
		}
		return k % 2 == 0 ? evenProfits[prices.length - 1] : oddProfits[prices.length - 1];
	}

	// solution 1
	public static int maxProfitWithKTransactions1(int[] prices, int k) {
		if (prices.length == 0) {
			return 0;
		}

		int[][] profits = new int[k + 1][prices.length];
		for (int t = 1; t < k + 1; t++) {
			int maxThusFar = Integer.MIN_VALUE;
			for (int d = 1; d < prices.length; d++) {
				maxThusFar = Math.max(maxThusFar, profits[t - 1][d - 1] - prices[d - 1]);
				profits[t][d] = Math.max(profits[t][d - 1], maxThusFar + prices[d]);
			}
		}
		return profits[k][prices.length - 1];
	}

	public static void main(String[] args) {
		int[] input = { 5, 11, 3, 50, 60, 90 };
		assertTrue(MaxProfitWithKTransactions.maxProfitWithKTransactions(input, 2) == 93);

	}

}
