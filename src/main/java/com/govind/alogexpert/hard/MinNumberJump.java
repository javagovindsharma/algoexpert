package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class MinNumberJump {
	// solution 1
	public static int minNumberOfJumps1(int[] array) {
		int[] jumps = new int[array.length];
		Arrays.fill(jumps, Integer.MAX_VALUE);
		jumps[0] = 0;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[j] >= i - j) {
					jumps[i] = Math.min(jumps[j] + 1, jumps[i]);
				}
			}
		}
		return jumps[jumps.length - 1];
	}

//solution 2
	public static int minNumberOfJumps(int[] array) {
		if(array.length==1) {
			return 0;
		}	
		int jumps= 0;
		int maxReach=array[0];
		int steps=array[0];
		
		for (int i = 1; i < array.length; i++) {
			maxReach=Math.max(maxReach, i+array[i]);
			steps--;
			if(steps==0) {
				jumps++;
				steps=maxReach-i;
			}
		}
		return jumps+ 1;
	}

	public static void main(String[] args) {
		 int[] input = {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3};
		 assertTrue(MinNumberJump.minNumberOfJumps(input) == 4);

	}

}
