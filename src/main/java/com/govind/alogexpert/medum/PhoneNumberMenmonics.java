package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberMenmonics {

	public static Map<Character, String[]> deleteDigit = new HashMap<Character, String[]>();
	static {
		deleteDigit.put('0', new String[] { "0" });
		deleteDigit.put('1', new String[] { "1" });
		deleteDigit.put('2', new String[] { "a", "b", "c" });
		deleteDigit.put('3', new String[] { "d", "e", "f" });
		deleteDigit.put('4', new String[] { "g", "h", "i" });
		deleteDigit.put('5', new String[] { "j", "k", "l" });
		deleteDigit.put('6', new String[] { "m", "n", "o" });
		deleteDigit.put('7', new String[] { "p", "q", "r", "s" });
		deleteDigit.put('8', new String[] { "t", "u", "v" });
		deleteDigit.put('9', new String[] { "w", "x", "y", "z" });

	}

	public static ArrayList<String> phoneNumberMnemonics(String phoneNumber) {

		String[] currentMnemonic = new String[phoneNumber.length()];
		Arrays.fill(currentMnemonic, "0");
		ArrayList<String> mnemonics = new ArrayList<String>();
		phoneNumberMnemonicsHelper(0, phoneNumber, currentMnemonic, mnemonics);
		return mnemonics;
	}

	private static void phoneNumberMnemonicsHelper(int idx, String phoneNumber, String[] currentMnemonic,
			ArrayList<String> mnemonicsFound) {

		if (idx == phoneNumber.length()) {
			String mnemonic = String.join("", currentMnemonic);
			mnemonicsFound.add(mnemonic);
		} else {
			char digit = phoneNumber.charAt(idx);
			String[] letters = deleteDigit.get(digit);
			for (String letter : letters) {
				currentMnemonic[idx] = letter;
				phoneNumberMnemonicsHelper(idx + 1, phoneNumber, currentMnemonic, mnemonicsFound);
			}
		}

	}
	
	
	public static void main(String[] args) {
		String str = "1905";
		System.out.println(phoneNumberMnemonics(str));
	}
}
