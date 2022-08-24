package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseSize {
	public static List<Integer> riverSizes(int[][] matrix) {
		// Write your code here.
		return new ArrayList<Integer>();
	}

	public static void main(String[] args) {
		int[][] input = {
			      {1, 0, 0, 1, 0},
			      {1, 0, 1, 0, 0},
			      {0, 0, 1, 0, 1},
			      {1, 0, 1, 0, 1},
			      {1, 0, 1, 1, 0},
			    };
			    int[] expected = {1, 2, 2, 2, 5};
			    List<Integer> output = ReverseSize.riverSizes(input);
			    Collections.sort(output);
			    System.out.println(output+"\n"+ expected);

	}

}
