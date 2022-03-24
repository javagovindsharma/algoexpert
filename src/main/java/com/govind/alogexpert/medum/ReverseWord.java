package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseWord {
	public static String reverseWordsInString2(String string) {
		char[] character=string.toCharArray();
		reverseListRange(character,0,character.length-1);
		int startOfWord=0;
		while(startOfWord<character.length ) {
			int endOfWord=startOfWord;
			while(endOfWord<character.length && character[endOfWord]!=' ') {
				endOfWord+=1;
			}
			reverseListRange(character,startOfWord,endOfWord);
			startOfWord=endOfWord+1;
		}
		return new String(character);
	}

	public static  char[] reverseListRange(char[] list, int start, int end) {
        
		 while(start<end) {
			 char temp=list[start];
			 list[start]=list[end];
			 list[end]=temp;
			 start+=1;
			 end-=1;
		 }
		return list;
	}

	public static String reverseWordsInString1(String string) {
		List<String> list = new ArrayList<String>();
		int startIdex = 0;
		for (int idx = 0; idx < string.length(); idx++) {
			char character = string.charAt(idx);
			if (character == ' ') {
				list.add(string.substring(startIdex, idx));
				startIdex = idx;
			} else if (string.charAt(startIdex) == ' ') {
				list.add(" ");
				startIdex = idx;
			}
		}
		list.add(string.substring(startIdex));
		Collections.reverse(list);
		return String.join("", list);
	}

	public static void main(String[] args) {
		// String str= "test ";
		// String str="AlgoExpert is the best!";
		String str = "    Hello I'm your       String    ";
		System.out.println(reverseWordsInString2(str));
	}

}
