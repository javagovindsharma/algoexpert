package com.govind.alogexpert.test;

import static org.junit.Assert.assertTrue;

public class LongestStreakOfAdjacentOnes {

	public static int longestStreakOfAdjacentOnes(int[] array) {
		int longestStrakLength=0;
		int longestStrakrelacezeroIdx=-1;
		
		int currentStreaklength=0;
		int replacedZeroIdx=-1;
		
		for(int i=0;i<array.length;i++) {
			if(array[i]==1) {
				currentStreaklength++;
			}else {
				currentStreaklength=i-replacedZeroIdx;
				replacedZeroIdx=i;
			}
			
			if(currentStreaklength>longestStrakLength) {
				longestStrakLength=currentStreaklength;
				longestStrakrelacezeroIdx=replacedZeroIdx;
			}
		}
		return longestStrakrelacezeroIdx;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1 };
		assertTrue(LongestStreakOfAdjacentOnes.longestStreakOfAdjacentOnes(array) == 8);

	}

}
