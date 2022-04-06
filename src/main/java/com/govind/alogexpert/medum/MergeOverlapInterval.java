package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverlapInterval {
	
	public static int[][] mergeOverlappingIntervals(int[][] intervals) {
        int[][] sortedInterval=intervals.clone();
        Arrays.sort(sortedInterval,(a,b)->Integer.compare(a[0], b[0]));
        
        List<int[]> mergeList=new ArrayList<int[]>();
        int[] currentInterval=sortedInterval[0];
        mergeList.add(currentInterval);
        
        for(int[] nextIntervals:sortedInterval) {
        	int currentEndVal=currentInterval[1];
        	int nextIntervalStart=nextIntervals[0];
        	int nextIntervalEnd=nextIntervals[1];
        	
        	if(currentEndVal>=nextIntervalStart) {
        		 currentInterval[1]=Math.max(currentEndVal, nextIntervalEnd);
        	}else {
        		currentInterval=nextIntervals;
        		mergeList.add(currentInterval);
        	}
        }
        
		return mergeList.toArray(new int[mergeList.size()][]);
	}

	public static void main(String[] args) {
		int[][] intervals = new int[][] { { 1, 2 }, { 3, 5 }, { 4, 7 }, { 6, 8 }, { 9, 10 } };
	  int[][] aa=mergeOverlappingIntervals(intervals);
	  System.out.println(Arrays.deepToString(aa));
	}

}
