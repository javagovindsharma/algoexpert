package com.govind.alogexpert.easy;

public class PalindromeCheck {

	public static boolean isPalindrome1(String str) {
		boolean flag = false;
		for (int i = 0; i <= str.length() / 2; i++) {
			if (str.charAt(i) == str.charAt(str.length() - 1 - i)) {
				flag = true;
			} else {
				flag = false;
				break;
			}

		}
		return flag;
	}

	public static boolean isPalindrome2(String str) {
		//boolean flag = false;
		StringBuilder input = new StringBuilder();
		input.append(str);
		input.reverse();

		return str.equals(input.toString());
	}

	public static boolean isPalindrome3(String str) {
		boolean flag = false;
		StringBuilder input = new StringBuilder();
		input.append(str);
		input.reverse();

		for (int i = 0; i <= str.length() / 2; i++) {
			if (str.charAt(i) == input.charAt(i)) {
				flag = true;
			} else {
				flag = false;
				break;
			}

		}
		return flag;

	}

	public static boolean isPalindrome4(String str) {
		return isPlaindromeCheck(str, 0);
	}

	private static boolean isPlaindromeCheck(String str, int i) {
		int j = str.length() - 1 - i;
		return i >= j ? true : str.charAt(i) == str.charAt(j) && isPlaindromeCheck(str, i + 1);
	}

	public static boolean isPalindrome(String str) {
       int leftIdx=0;
       int rightIdx=str.length()-1;
       while(leftIdx<rightIdx) {
    	    if(str.charAt(leftIdx)!=str.charAt(rightIdx)) {
    	    	return false;
    	    }
    	    leftIdx++;
    	    rightIdx--;
       }
		  return true;
	}

	public static void main(String[] args) {
		String str = "abcdefghihgfeddcba";
		System.out.println(isPalindrome(str));

	}

}
