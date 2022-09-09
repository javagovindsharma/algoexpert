package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubSequence {

	// solution 1
	public static List<Character> longestCommonSubsequence1(String str1, String str2) {
		List<List<List<Character>>> lcs = new ArrayList<List<List<Character>>>();
		for (int i = 0; i < str2.length() + 1; i++) {
			lcs.add(new ArrayList<List<Character>>());
			for (int j = 0; j < str1.length() + 1; j++) {
				lcs.get(i).add(new ArrayList<Character>());
			}
		}

		for (int i = 1; i < str2.length() + 1; i++) {
			for (int j = 1; j < str1.length() + 1; j++) {
				if (str2.charAt(i - 1) == str1.charAt(j - 1)) {
					List<Character> copy = new ArrayList<Character>(lcs.get(i - 1).get(j - 1));
					lcs.get(i).set(j, copy);
					lcs.get(i).get(j).add(str2.charAt(i - 1));
				} else if (lcs.get(i - 1).get(j).size() > lcs.get(i).get(j - 1).size()) {
					lcs.get(i).set(j, lcs.get(i - 1).get(j));
				} else {
					lcs.get(i).set(j, lcs.get(i).get(j - 1));
				}
			}
		}
		return lcs.get(str2.length()).get(str1.length());
	}

	// solution 2
	public static List<Character> longestCommonSubsequence2(String str1, String str2) {
		String small = str1.length() < str2.length() ? str1 : str2;
		String big = str1.length() >= str2.length() ? str1 : str2;

		List<List<Character>> evenLcs = new ArrayList<List<Character>>();
		List<List<Character>> oddLcs = new ArrayList<List<Character>>();

		for (int i = 0; i < small.length() + 1; i++) {
			evenLcs.add(new ArrayList<Character>());
		}
		for (int i = 0; i < small.length() + 1; i++) {
			oddLcs.add(new ArrayList<Character>());
		}

		for (int i = 1; i < big.length() + 1; i++) {
			List<List<Character>> currentLcs;
			List<List<Character>> previousLcs;

			if (i % 2 == 1) {
				currentLcs = oddLcs;
				previousLcs = evenLcs;
			} else {
				currentLcs = evenLcs;
				previousLcs = oddLcs;
			}

			for (int j = 1; j < small.length() + 1; j++) {
				if (big.charAt(i - 1) == small.charAt(j - 1)) {
					List<Character> copy = new ArrayList<Character>(previousLcs.get(j - 1));
					currentLcs.set(j, copy);
					currentLcs.get(j).add(big.charAt(i - 1));
				} else {
					if (previousLcs.get(j).size() > currentLcs.get(j - 1).size()) {
						currentLcs.set(j, previousLcs.get(j));
					} else {
						currentLcs.set(j, currentLcs.get(j - 1));
					}
				}
			}
		}
		return big.length() % 2 == 0 ? evenLcs.get(small.length()) : oddLcs.get(small.length());
	}

	// solution 3
	public static List<Character> longestCommonSubsequence3(String str1, String str2) {
		int[][][] lcs = new int[str2.length() + 1][str1.length() + 1][];

		for (int i = 0; i < str2.length() + 1; i++) {
			for (int j = 0; j < str1.length() + 1; j++) {
				lcs[i][j] = new int[] { 0, 0, 0, 0 };
			}
		}

		for (int i = 1; i < str2.length() + 1; i++) {
			for (int j = 1; j < str1.length() + 1; j++) {
				if (str2.charAt(i - 1) == str1.charAt(j - 1)) {
					int[] newEntry = { (int) str2.charAt(i - 1), lcs[i - 1][j - 1][1] + 1 };
					lcs[i][j] = newEntry;
				} else {
					if (lcs[i - 1][j][1] > lcs[i][j - 1][1]) {
						int[] newEntry = { -1, lcs[i - 1][j][1], i - 1, j };
						lcs[i][j] = newEntry;
					} else {
						int[] newEntry = { -1, lcs[i][j - 1][1], i, j - 1 };
						lcs[i][j] = newEntry;
					}
				}
			}
		}
		return buildSequence(lcs);
	}

	private static List<Character> buildSequence(int[][][] lcs) {
		List<Character> sequence = new ArrayList<Character>();
		int i = lcs.length - 1;
		int j = lcs[0].length - 1;
		while (i != 0 && j != 0) {
			int[] currentEntry = lcs[i][j];
			if (currentEntry[0] != -1) {
				sequence.add(0, (char) currentEntry[0]);
			}
			i = currentEntry[2];
			j = currentEntry[3];
		}
		return sequence;
	}

	// solution 4
	public static List<Character> longestCommonSubsequence(String str1, String str2) {
       int[][] lengths=new int[str2.length()+1][str1.length()+1];
       for(int i=1;i<str2.length()+1;i++) {
    	    for(int j=1;j<str1.length()+1;j++) {
    	    	if(str2.charAt(i-1)==str1.charAt(j-1)) {
    	    		lengths[i][j]=lengths[i-1][j-1]+1;
    	    	}else {
    	    		lengths[i][j]=Math.max(lengths[i-1][j], lengths[i][j-1]);
    	    	}
    	    }
       }
       return buildSequence(lengths,str1);
	}

	private static List<Character> buildSequence(int[][] lengths, String str) {
		List<Character> sequence=new ArrayList<Character>();
		int i=lengths.length-1;
		int j=lengths[0].length-1;
		
		while(i!=0 && j!=0) {
			if(lengths[i][j]==lengths[i-1][j]) {
				i--;
			}else if(lengths[i][j]==lengths[i][j-1]) {
				j--;
			}else {
				sequence.add(0,str.charAt(j-1));
				i--;
				j--;
			}
		}
		return sequence;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
