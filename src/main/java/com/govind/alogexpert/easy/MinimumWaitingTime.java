package com.govind.alogexpert.easy;

import java.util.Arrays;

public class MinimumWaitingTime {

	public int minimumWaitingTime(int[] queries) {
		Arrays.sort(queries);
		int totalWaitingTime = 0;
		for(int idx=0;idx<queries.length;idx++) {
			int duration=queries[idx];
			int queriesLeft = queries.length - (idx+1);
			totalWaitingTime +=duration * queriesLeft;
		}
		return totalWaitingTime;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
