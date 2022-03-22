package com.govind.alogexpert.easy;

import java.util.HashMap;
import java.util.Map;

public class RunLengthEncoding {
	public static String runLengthEncodingNotUseable(String string) {
		Map<Character, Integer> maps = new HashMap<Character, Integer>();
		for (char ch : string.toCharArray()) {
			if (maps.containsKey(ch)) {
				maps.put(ch, maps.get(ch) + 1);
			} else {
				maps.put(ch, 1);
			}
		}
		String str = "";
		for (Map.Entry<Character, Integer> entry : maps.entrySet()) {
			int value = entry.getValue();
			if (value > 8) {
				int times = value / 9;
				int rem = value % 9;
				for (int i = 0; i < times; i++) {
					str += 9 + "" + entry.getKey();
					value -= 9;
				}
				str += rem + "" + entry.getKey();
			} else {
				str += value + "" + entry.getKey();
			}
		}
		return str;
	}

	public static String runLengthEncoding(String string) {
		StringBuilder encodedStringCharacter = new StringBuilder();
		int currentRunLength = 1;
		for (int i = 0; i < string.length(); i++) {
			Character currentChar = string.charAt(i);
			Character previousChar = string.charAt(i - 1);
			if (currentChar != previousChar || (currentRunLength == 9)) {
				encodedStringCharacter.append(currentRunLength);
				encodedStringCharacter.append(previousChar);
				currentRunLength = 0;
			}
			currentRunLength += 1;
		}
		encodedStringCharacter.append(Integer.toString(currentRunLength));
		encodedStringCharacter.append(string.charAt(string.length() - 1));

		return encodedStringCharacter.toString();
	}

	public static void main(String[] args) {
		String str = "AAAAAAAAAAAAAAAAAAAABBCCCCDD";
		System.out.println(runLengthEncoding(str));

	}

}
