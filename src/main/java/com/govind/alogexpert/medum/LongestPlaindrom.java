package com.govind.alogexpert.medum;

public class LongestPlaindrom {

	public static String longestPalindromicSubstring1(String str) {
		String longest = "";
		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				String substring = str.substring(i, j + 1);
				if (substring.length() > longest.length() && isPalindrome1(substring)) {
					longest = substring;
				}
			}
		}
		return longest;
	}

	public static boolean isPalindrome1(String str) {

		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static String longestPalindromicSubstring(String str) {
		int[] currentlongest = { 0, 1 };
		for (int i = 1; i < str.length(); i++) {
			int[] odd = getPalindromeFrom(str, i - 1, i + 1);
			int[] even = getPalindromeFrom(str, i - 1, i);
			int[] longest=odd[1]-odd[0]>even[1]-even[0]?odd:even;
			currentlongest=currentlongest[1]-currentlongest[0]>longest[1]-longest[0]?currentlongest:longest;
		}
		return str.substring(currentlongest[0], currentlongest[1]);
	}

	public static int[] getPalindromeFrom(String str, int left, int right) {
		while (left>=0 && right<str.length()) {
			if (str.charAt(left) != str.charAt(right)) {
				break;
			}
			left--;
			right++;
		}
		return new int[] { left + 1, right };
	}

	public static void main(String[] args) {

		System.out.println(longestPalindromicSubstring("abaxyzzyxf"));
	}

}
