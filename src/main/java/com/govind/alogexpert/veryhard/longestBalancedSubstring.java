package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.Stack;

public class longestBalancedSubstring {

	// solution 4
	public int longestBalancedSubstring(String string) {
		return Math.max(getLongestBalancedInDirection(string, true), getLongestBalancedInDirection(string, false));
	}

	private int getLongestBalancedInDirection(String string, boolean leftToRight) {
		char opeingParens = leftToRight ? '(' : ')';
		int startIdx = leftToRight ? 0 : string.length();
		int step = leftToRight ? 1 : -1;

		int maxlength = 0;
		int openingCount = 0;
		int closingCount = 0;
		int idx = startIdx;
		while (idx >= 0 && idx < string.length()) {
			char c = string.charAt(idx);

			if (c == opeingParens) {
				openingCount += 1;
			} else {
				closingCount += 1;
			}

			if (openingCount == closingCount) {
				maxlength = Math.max(maxlength, closingCount * 2);
			} else if (closingCount > openingCount) {
				openingCount = 0;
				closingCount = 0;
			}
			idx += step;
		}

		return maxlength;
	}

	// solution 3
	public int longestBalancedSubstring3(String string) {
		int maxlength = 0;
		int openingCount = 0;
		int closingCount = 0;

		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);

			if (c == '(') {
				openingCount += 1;
			} else {
				closingCount += 1;
			}

			if (openingCount == closingCount) {
				maxlength = Math.max(maxlength, closingCount * 2);
			} else if (closingCount > openingCount) {
				openingCount = 0;
				closingCount = 0;
			}
		}

		openingCount = 0;
		closingCount = 0;

		for (int i = string.length() - 1; i >= 0; i--) {
			char c = string.charAt(i);
			if (c == '(') {
				openingCount += 1;
			} else {
				closingCount += 1;
			}
			if (openingCount == closingCount) {
				maxlength = Math.max(maxlength, openingCount * 2);
			} else if (openingCount > closingCount) {
				openingCount = 0;
				closingCount = 0;
			}
		}
		return maxlength;
	}

	// solution 2
	public int longestBalancedSubstring2(String string) {
		int maxlength = 0;
		Stack<Integer> idxStack = new Stack();
		idxStack.push(-1);

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '(') {
				idxStack.push(i);
			} else {
				idxStack.pop();
				if (idxStack.size() == 0) {
					idxStack.push(i);
				} else {
					int balancedSubstringStartIdx = idxStack.peek();
					int currentLength = i - balancedSubstringStartIdx;
					maxlength = Math.max(maxlength, currentLength);
				}
			}
		}
		return maxlength;
	}

	// SOLUTION 1
	public int longestBalancedSubstring1(String string) {
		int maxlength = 0;
		for (int i = 0; i < string.length(); i++) {
			for (int j = i + 2; j < string.length() + 1; j += 2) {
				if (isBalanced(string.substring(i, j))) {
					int currentlength = j - i;
					maxlength = Math.max(maxlength, currentlength);
				}
			}
		}
		return maxlength;
	}

	private boolean isBalanced(String string) {
		Stack<Character> openParensStack = new Stack();

		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '(') {
				openParensStack.push('(');
			} else if (openParensStack.size() > 0) {
				openParensStack.pop();
			} else {
				return false;
			}
		}
		return openParensStack.size() == 0;
	}

	public static void main(String[] args) {
		String input = "(()))(";
		int expected = 4;
		int actual = new longestBalancedSubstring().longestBalancedSubstring(input);
		assertTrue(expected == actual);

	}

}
