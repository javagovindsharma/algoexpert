package com.govind.alogexpert.hard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


public class UnderscorifySubstring {
	public static String underscorifySubstring(String str, String substring) {
		List<Integer[]> locations = collapse(getLocations(str, substring));
		return underscorify(str, locations);
	}

	private static List<Integer[]> collapse(List<Integer[]> locations) {
		if (locations.size() == 0) {
			return locations;
		}

		List<Integer[]> newLocations = new ArrayList<Integer[]>();
		newLocations.add(locations.get(0));
		Integer[] previous = newLocations.get(0);
		for (int i = 1; i < locations.size(); i++) {
			Integer[] current = locations.get(i);
			if (current[0] <= previous[1]) {
				previous[1] = current[1];
			} else {
				newLocations.add(current);
				previous = current;
			}
		}
		return newLocations;
	}

	private static String underscorify(String str, List<Integer[]> locations) {
		int locationsIdx = 0;
		int stringIdx = 0;
		boolean inBetweenUnderscores = false;
		List<String> finalChars = new ArrayList<String>();
		int i = 0;
		while (stringIdx < str.length() && locationsIdx < locations.size()) {
			if (stringIdx == locations.get(locationsIdx)[i]) {
				finalChars.add("_");
				inBetweenUnderscores = !inBetweenUnderscores;
				if (!inBetweenUnderscores) {
					locationsIdx++;
				}
				i = i == 1 ? 0 : 1;
			}
			finalChars.add(String.valueOf(str.charAt(stringIdx)));
			stringIdx += 1;
		}
			if (locationsIdx < locations.size()) {
				finalChars.add("_");
			} else if (stringIdx < str.length()) {
				finalChars.add(str.substring(stringIdx));
			}
		
		return String.join("", finalChars);
	}

	private static List<Integer[]> getLocations(String str, String substring) {
		List<Integer[]> locations = new ArrayList<Integer[]>();
		int startIdx = 0;
		while (startIdx < str.length()) {
			int nextIdx = str.indexOf(substring, startIdx);
			if (nextIdx != -1) {
				locations.add(new Integer[] { nextIdx, nextIdx + substring.length() });
				startIdx = nextIdx + 1;
			} else {
				break;
			}
		}
		return locations;
	}

	public static void main(String[] args) {
		String expected = "_test_this is a _testtest_ to see if _testestest_ it works";
		String output = UnderscorifySubstring.underscorifySubstring("testthis is a testtest to see if testestest it works", "test");
		assertEquals(expected,output);

	}

}
