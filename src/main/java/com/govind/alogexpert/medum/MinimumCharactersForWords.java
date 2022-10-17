package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumCharactersForWords {

	public char[] minimumCharactersForWords(String[] words) {
	    HashMap<Character,Integer> maximumCharacterFrequencies= new HashMap<Character,Integer>();
	    for(String word:words) {
	    	HashMap<Character,Integer> characterFrequencies=countCharacterFrequencies(word);
	    	updateMaximumFrequencies(characterFrequencies,maximumCharacterFrequencies);
	    }
		return makeArrayFromCharacterFrequencies(maximumCharacterFrequencies);
	}

	public HashMap<Character,Integer> countCharacterFrequencies(String string){
		HashMap<Character,Integer> characterFrequencies= new HashMap<Character,Integer>();
	     for(char character:string.toCharArray()) {
	    	 characterFrequencies.put(character,characterFrequencies.getOrDefault(character,0)+1);
	     }
		return characterFrequencies;
	}
	public void updateMaximumFrequencies(HashMap<Character,Integer> frequencies,HashMap<Character,Integer> maximumFrequencies) {
		for(Map.Entry<Character, Integer> map:frequencies.entrySet()) {
			  char character=map.getKey();
			  int characterFrequeny=map.getValue();
			  if(maximumFrequencies.containsKey(character)) {
				  maximumFrequencies.put(character, Math.max(characterFrequeny,maximumFrequencies.get(character)));
			  }else {
				  maximumFrequencies.put(character, characterFrequeny);
			  }
		}
	}
	
	public char[] makeArrayFromCharacterFrequencies(HashMap<Character,Integer> characterFrequencies) {
		ArrayList<Character> characters=new ArrayList<Character>();
		for(Map.Entry<Character, Integer> map:characterFrequencies.entrySet()) {
			  char character=map.getKey();
			  int characterFrequeny=map.getValue();
			  
			  for(int idx=0;idx<characterFrequeny;idx++) {
				  characters.add(character);
			  }
		}
		char[] characterArray=new char[characters.size()];
		for(int idx=0;idx<characters.size();idx++) {
			characterArray[idx]=characters.get(idx);
		}
		return characterArray;
	}
	
	public static void main(String[] args) {
		 String[] words = new String[] {"this", "that", "did", "deed", "them!", "a"};
		  //  char[] expected = new char[] {'t', 't', 'h', 'i', 's', 'a', 'd', 'd', 'e', 'e', 'm', '!'};
		    char[] actual = new MinimumCharactersForWords().minimumCharactersForWords(words);
            System.out.println(Arrays.toString(actual));
	}

}
