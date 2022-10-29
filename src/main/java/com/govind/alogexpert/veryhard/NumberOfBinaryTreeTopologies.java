package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfBinaryTreeTopologies {

	// solution 3
	public static int numberOfBinaryTreeTopologies(int n) {
		List<Integer> cache = new ArrayList<>();
		cache.add(1);
		for (int m = 1; m < n + 1; m++) {
			int numberOfTrees = 0;
			for (int leftTreeSize = 0; leftTreeSize < m; leftTreeSize++) {
				int rightTreeSize = m - 1 - leftTreeSize;
				int numberOfLeftTrees = cache.get(leftTreeSize);
				int numberOfRightTrees = cache.get(rightTreeSize);
				numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
			}
			cache.add(numberOfTrees);
		}
		return cache.get(n);
	}

	// solution 2
	public static int numberOfBinaryTreeTopologies2(int n) {
		Map<Integer, Integer> cache = new HashMap<>();
		cache.put(0, 1);
		return numberOfBinaryTreeTopologies(n, cache);
	}

	private static int numberOfBinaryTreeTopologies(int n, Map<Integer, Integer> cache) {
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		int numberOfTrees = 0;
		for (int leftTreeSize = 0; leftTreeSize < n; leftTreeSize++) {
			int rightTreeSize = n - 1 - leftTreeSize;
			int numberOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize, cache);
			int numberOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize, cache);
			numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
		}
		cache.put(n, numberOfTrees);
		return numberOfTrees;
	}

	// solution 1
	public static int numberOfBinaryTreeTopologies1(int n) {
		if (n == 0) {
			return 1;
		}

		int numberOfTrees = 0;
		for (int leftTreeSize = 0; leftTreeSize < n; leftTreeSize++) {
			int rightTreeSize = n - 1 - leftTreeSize;
			int numberOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize);
			int numberOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize);
			numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
		}

		return numberOfTrees;
	}

	public static void main(String[] args) {
		assertTrue(NumberOfBinaryTreeTopologies.numberOfBinaryTreeTopologies(3) == 5);

	}

}
