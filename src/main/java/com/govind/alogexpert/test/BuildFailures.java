package com.govind.alogexpert.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class BuildFailures {

	public static int buildFailures(boolean[][] buildRuns) {
		ArrayList<Double> greenpercentages=new ArrayList<Double>();
		for(boolean[] buildRun:buildRuns) {
			greenpercentages.add(calculateGreenPercentag(buildRun));
		}
		return getLongestDecreaseingSubarraylength(greenpercentages);
	}

	private static Double calculateGreenPercentag(boolean[] buildRun) {
		int firstFalseIdx=binarysearchForFirstFalse(buildRun,0,buildRun.length);
		return (double)firstFalseIdx/buildRun.length;
	}

	private static int binarysearchForFirstFalse(boolean[] array, int leftIdx, int rightIdx) {
		  if(leftIdx>rightIdx) return -1;
		  
		  int middleIdx=(leftIdx+rightIdx)/2;
		  boolean isFalse=!array[middleIdx];
		  if(isFalse) {
			  boolean isFirstFalse=middleIdx==0 || array[middleIdx-1];
			  if(isFirstFalse) {
				  return middleIdx;
			  }else {
				  return binarysearchForFirstFalse(array, leftIdx, middleIdx-1);
			  }
		  }else {
			  return binarysearchForFirstFalse(array, middleIdx+1,rightIdx); 
		  }
	}

	private static int getLongestDecreaseingSubarraylength(ArrayList<Double> array) {
		int longestLength=1;
		int currentLongestLength=1;
		
		for(int i=1;i<array.size();i++) {
			if(array.get(i)<array.get(i-1)) {
				currentLongestLength++;
			     longestLength=Math.max(longestLength, currentLongestLength);	
			}else {
				currentLongestLength=1;
			}
		}
		
		return longestLength>1?longestLength:-1;
	}

	public static void main(String[] args) {
		boolean[][] buildRuns = new boolean[][] { { true, true, true, false, false }, { true, true, true, true, false },
				{ true, true, true, true, true, true, false, false, false },
				{ true, false, false, false, false, false },
				{ true, true, true, true, true, true, true, true, true, true, true, true, false }, { true, false },
				{ true, true, true, true, false, false }, };
		assertTrue(BuildFailures.buildFailures(buildRuns) == 3);

	}

}
