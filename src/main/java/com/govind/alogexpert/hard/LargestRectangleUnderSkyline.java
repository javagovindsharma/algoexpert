package com.govind.alogexpert.hard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleUnderSkyline {

	//solution 1
	public int largestRectangleUnderSkyline1(ArrayList<Integer> buildings) {
		int maxArea=0;
		for(int pillarIdx=0;pillarIdx<buildings.size();pillarIdx++) {
			 int currentHeight=buildings.get(pillarIdx);
			 int furthesleft=pillarIdx;
			 while(furthesleft>0 && buildings.get(furthesleft-1) >=currentHeight) {
				furthesleft-=1; 
			 }
			 int furthestRight=pillarIdx;
			 while(furthestRight<buildings.size()-1 && buildings.get(furthestRight+1)>=currentHeight) {
				 furthestRight+=1;
			 }
			 int areaWithCurrentBuilding=(furthestRight-furthesleft+1)* currentHeight;
			 maxArea =Math.max(areaWithCurrentBuilding, maxArea);
		}
		return maxArea;
	}

	//solution 2 
	public int largestRectangleUnderSkyline(ArrayList<Integer> buildings) {
		Stack<Integer> pillarIndices=new Stack<Integer>();
		int maxArea=0;
		
		ArrayList<Integer> extendedBuildings=new ArrayList<Integer>(buildings);
		extendedBuildings.add(0);
		
		for(int idx=0;idx<extendedBuildings.size();idx++) {
			 int height=extendedBuildings.get(idx);
			 while(!pillarIndices.isEmpty() && extendedBuildings.get(pillarIndices.peek()) >=height) {
				 int pillarHeight=extendedBuildings.get(pillarIndices.pop());
				 int width =(pillarIndices.isEmpty())?idx:idx-pillarIndices.peek()-1;		 
				 maxArea =Math.max(width*pillarHeight, maxArea);
			 }	
			 pillarIndices.push(idx);
		}
		return maxArea;
	}
	
	public static void main(String[] args) {
		  ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 2, 4, 1, 5, 3, 2));
		    int expected = 9;
		    int actual = new LargestRectangleUnderSkyline().largestRectangleUnderSkyline(input);
		    assertEquals(expected , actual);

	}

}
