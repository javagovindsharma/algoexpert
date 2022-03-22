package com.govind.alogexpert.easy;

public class CaeserCiperEncoding {
	public static String caesarCypherEncryptor1(String str, int key) {
		String msg = "";
		key = key % 26;
		for (Character ch : str.toCharArray()) {
			int chval = (int) ch;

			chval += key;

			if (chval > 122)
				chval -= 26;
			msg += (char) chval;
		}
		return msg;
	}

	public static String caesarCypherEncryptor2(String str, int key) {
		char[] newletter = new char[str.length()];
		int newKey = key % 26;
		for (int i = 0; i < str.length(); i++) {
			newletter[i] = getNewLetter2(str.charAt(i), newKey);
		}
		return new String(newletter);
	}

	private static char getNewLetter2(char letter, int newKey) {
		int newLetterCode = letter + newKey;

		return newLetterCode <= 122 ? (char) newLetterCode : (char) (96 + newLetterCode % 122);
	}

	public static String caesarCypherEncryptor(String str, int key) {
		char[] newletter = new char[str.length()];
		int newKey = key % 26;
		String alpabet = "abcdefghijklmonpqrstuvwxyz";
		for (int i = 0; i < str.length(); i++) {
			newletter[i] = getNewLetter(str.charAt(i), newKey, alpabet);
		}
		return new String(newletter);
	}

	private static char getNewLetter(char letter, int newKey, String alpabet) {
		int newLetterCode = alpabet.indexOf(letter) + newKey;
		return alpabet.charAt(newLetterCode % 26);
	}

	public static void main(String[] args) {
		String str = "abc";
		int key = 52;
		System.out.println(caesarCypherEncryptor(str, key));

	}

}
