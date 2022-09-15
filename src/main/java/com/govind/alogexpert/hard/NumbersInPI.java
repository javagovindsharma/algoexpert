package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumbersInPI {

	// solution 1
	public static int numbersInPi(String pi, String[] numbers) {
		Set<String> numbersTable = new HashSet<String>();
		for (String number : numbers) {
			numbersTable.add(number);
		}
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
		for (int i = pi.length() - 1; i >= 0; i--) {
			int minSpaces = getMinSpaces(pi, numbersTable, cache, i);
		}
		return cache.get(0)==Integer.MAX_VALUE?-1:cache.get(0);
	}

	// solution 2
	public static int numbersInPi1(String pi, String[] numbers) {
		Set<String> numbersTable = new HashSet<String>();
		for (String number : numbers) {
			numbersTable.add(number);
		}
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
		int minSpaces = getMinSpaces(pi, numbersTable, cache, 0);
		return minSpaces == Integer.MAX_VALUE ? -1 : minSpaces;
	}

	private static int getMinSpaces(String pi, Set<String> numbersTable, Map<Integer, Integer> cache, int idx) {
		if (idx == pi.length())
			return -1;
		if (cache.containsKey(idx))
			return cache.get(idx);
		int minSpaces = Integer.MAX_VALUE;
		for (int i = idx; i < pi.length(); i++) {
			String prefix = pi.substring(idx, i + 1);
			if (numbersTable.contains(prefix)) {
				int minSpaceInSuffix = getMinSpaces(pi, numbersTable, cache, i + 1);
				if (minSpaceInSuffix == Integer.MAX_VALUE) {
					minSpaces = Math.min(minSpaces, minSpaceInSuffix);
				} else {
					minSpaces = Math.min(minSpaces, minSpaceInSuffix + 1);
				}
			}
		}
		cache.put(idx, minSpaces);
		return cache.get(idx);
	}

	public static void main(String[] args) {
		String PI = "3141592653589793238462643383279";
		String[] numbers = new String[] { "314159265358979323846", "26433", "8", "3279", "314159265",
				"35897932384626433832", "79" };
		assertTrue(NumbersInPI.numbersInPi(PI, numbers) == 2);
	}

}
