package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupAnangram {
	 public static List<List<String>> groupAnagrams2(List<String> words) {
		 Map<String,List<String>> anagrams=new HashMap<String,List<String>>();
		 for (String word : words) {
				char[] charArray = word.toCharArray();
				Arrays.sort(charArray);
				String sortedWord = new String(charArray);
				
				if(anagrams.containsKey(sortedWord)) {
					anagrams.get(sortedWord).add(word);
				}else {
					anagrams.put(sortedWord, new ArrayList<String>(Arrays.asList(word)));
				}
				
			}
		    return new ArrayList<List<String>>(anagrams.values());
		  }
	@SuppressWarnings("unlikely-arg-type")
	public static List<List<String>> groupAnagrams(List<String> words) {
		if (words.size() == 0)
			return new ArrayList<List<String>>();

		List<String> sortedWords = new ArrayList<String>();

		for (String word : words) {
			char[] charArray = word.toCharArray();
			Arrays.sort(charArray);
			String sortedWord = new String(charArray);
			sortedWords.add(sortedWord);
		}

		List<Integer> indices = IntStream.range(0, words.size()).boxed().collect(Collectors.toList());
		indices.sort((a,b)->sortedWords.get(a).compareTo(sortedWords.get(b)));
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> currentAnagramGroup = new ArrayList<String>();
		String currentAnagram = sortedWords.get(indices.get(0));
		for (Integer index : indices) {
			String word = words.get(index);
			String sortedWord = sortedWords.get(index);
System.out.println(sortedWord+" =="+currentAnagram+""+sortedWords.equals(currentAnagram));
			if (sortedWord.equals(currentAnagram)) {
				
				currentAnagramGroup.add(word);
				continue;
			}
			result.add(currentAnagramGroup);
			currentAnagramGroup = new ArrayList<String>(Arrays.asList(word));
			currentAnagram = sortedWord;
		}
		result.add(currentAnagramGroup);

		return result;
	}

	public static void main(String[] args) {
		List<String> words = new ArrayList<String>(
				Arrays.asList("yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"));
		List<List<String>> expected = new ArrayList<List<String>>();
		expected.add(new ArrayList<String>(Arrays.asList("yo", "oy")));
		expected.add(new ArrayList<String>(Arrays.asList("flop", "olfp")));
		expected.add(new ArrayList<String>(Arrays.asList("act", "tac", "cat")));
		expected.add(new ArrayList<String>(Arrays.asList("foo")));
		List<List<String>> output = GroupAnangram.groupAnagrams(words);
		for (List<String> innerList : output) {
			System.out.println(innerList.toString());
		}

		System.out.println(new GroupAnangram().compare(expected, output));
	}

	public boolean compare(List<List<String>> expected, List<List<String>> output) {
		if (expected.size() != output.size())
			return false;

		for (List<String> group : expected) {
			Collections.sort(group);
			if (!output.contains(group))
				return false;
		}

		return true;
	}

}
