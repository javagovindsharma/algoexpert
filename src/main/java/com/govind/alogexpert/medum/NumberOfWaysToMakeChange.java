package com.govind.alogexpert.medum;

public class NumberOfWaysToMakeChange {
	public static int numberOfWaysToMakeChange(int n, int[] denoms) {
		int[] ways = new int[n+1];
		ways[0] = 1;
		for (int denom : denoms) {
			for (int amount = 1; amount <= n; amount++) {
				if (denom <= amount) {
					ways[amount] += ways[amount - denom];
				}
			}
		}
		return ways[n];
	}

	public static void main(String[] args) {
		int[] input = {1, 5};
	    System.out.println(numberOfWaysToMakeChange(6, input) == 2);

	}

}
