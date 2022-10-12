package com.govind.alogexpert.hard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShortenPath {
	public static String shortenPath(String path) {
		boolean startWithPath = path.charAt(0) == '/';
		String[] tokenArr = path.split("/");
		List<String> tokensList = Arrays.asList(tokenArr);
		List<String> filteredTokens = tokensList.stream().filter(token -> isImortantToken(token))
				.collect(Collectors.toList());

		List<String> stack = new ArrayList<String>();
		if (startWithPath)
			stack.add("");
		for (String token : filteredTokens) {
			if (token.equals("..")) {
				if (stack.size() == 0 || stack.get(stack.size() - 1).equals("..")) {
					stack.add(token);
				} else if (!stack.get(stack.size() - 1).equals("")) {
					stack.remove(stack.size() - 1);
				}
			} else {
				stack.add(token);
			}
		}
		if (stack.size() == 1 && stack.get(0).equals(""))
			return "/";

		return String.join("/", stack);
	}

	private static boolean isImortantToken(String token) {
		return token.length() > 0 && !token.equals(".");
	}

	public static void main(String[] args) {
		String expected = "/foo/bar/baz";
		String actual = ShortenPath.shortenPath("/foo/../test/../test/../foo//bar/./baz");
		assertEquals(expected, actual);

	}

}
