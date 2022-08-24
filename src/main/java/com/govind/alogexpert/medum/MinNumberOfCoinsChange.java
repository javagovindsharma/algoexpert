package com.govind.alogexpert.medum;

import java.util.Arrays;

public class MinNumberOfCoinsChange {

	public static int minNumberOfCoinsForChange(int n, int[] denoms) {
		int[] numOfCons=new int[n+1];
		Arrays.fill(numOfCons, Integer.MAX_VALUE);
		numOfCons[0]=0;
		int toCompare=0;
		for(int denom:denoms) {
			 for(int amount=0;amount<numOfCons.length;amount++){
				 if(denom<=amount) {
					 if(numOfCons[amount-denom]==Integer.MAX_VALUE) {
						 toCompare=numOfCons[amount-denom];
					 }else {
						 toCompare=numOfCons[amount-denom]+1;
					 }
					 numOfCons[amount]=Math.min(numOfCons[amount], toCompare);
				 }
			 }
				 
		}
		return numOfCons[n]!=Integer.MAX_VALUE?numOfCons[n]:-1;
	}

	public static void main(String[] args) {
		 int[] input = {1, 5, 10};
		System.out.println(MinNumberOfCoinsChange.minNumberOfCoinsForChange(7, input) == 3);
	}

}
