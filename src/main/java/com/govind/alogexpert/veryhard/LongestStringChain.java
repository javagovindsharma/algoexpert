package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestStringChain {

	static class stringChain {
		String nextString;
		Integer maxChainLength;

		public stringChain(String nextString, Integer maxChainLength) {
			this.nextString = nextString;
			this.maxChainLength = maxChainLength;
		}
	}

	public static List<String> longestStringChain(List<String> strings) {
		Map<String, stringChain> stringChains = new HashMap<String, stringChain>();
		for (String string : strings) {
			stringChains.put(string, new stringChain("", 1));
		}
		
		List<String> sortedStrings = new ArrayList<String>(strings);
		sortedStrings.sort((a, b) -> a.length() - b.length());

		for (String string : sortedStrings) {
			findLongestStringChain(string, stringChains);
		}
		return buildLongestStringChain(strings, stringChains);
	}

	private static void findLongestStringChain(String string, Map<String, stringChain> stringChains) {
		for (int i = 0; i < string.length(); i++) {
			String smallerString = getSmallerString(string, i);
			if (!stringChains.containsKey(smallerString))
				continue;
			tryUpdateLongestStringChain(string, smallerString, stringChains);
		}
	}

	private static String getSmallerString(String string, int index) {
		return string.substring(0, index) + string.substring(index + 1);
	}

	private static void tryUpdateLongestStringChain(String currentString, String smallerString,
			Map<String, stringChain> stringChains) {
		int smallerStringChainlength = stringChains.get(smallerString).maxChainLength;
		int currentStringChainlength = stringChains.get(currentString).maxChainLength;
		if (smallerStringChainlength + 1 > currentStringChainlength) {
			stringChains.get(currentString).maxChainLength = smallerStringChainlength+1;
			stringChains.get(currentString).nextString=smallerString;		
		}
	}

	private static List<String> buildLongestStringChain(List<String> strings, Map<String, stringChain> stringChains) {
		int maxChainLength=0;
		String chainStartingString="";
		for(String string:strings) {
			if(stringChains.get(string).maxChainLength>maxChainLength) {
				maxChainLength=stringChains.get(string).maxChainLength;
				chainStartingString=string;
			}
		}
		
		List<String> ourlongestStringChain=new ArrayList<String>();
		String currentString=chainStartingString;
		while(currentString!="") {
			ourlongestStringChain.add(currentString);
			currentString=stringChains.get(currentString).nextString;
		}
		return ourlongestStringChain.size()==1?new ArrayList<>():ourlongestStringChain;
	}

	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>(
				Arrays.asList("abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"));
		List<String> expected = new ArrayList<String>(Arrays.asList("abcdef", "abcde", "abde", "ade", "ae"));
		List<String> op=LongestStringChain.longestStringChain(strings);
		System.out.println(op.toString());
		assertTrue(LongestStringChain.longestStringChain(strings).equals(expected));

	}

}
