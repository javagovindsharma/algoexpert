package com.govind.alogexpert.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DegreesOfSeparation {

	public static String degreesOfSeparation(Map<String, String[]> friendsLists, String personOne, String personTwo) {
		HashMap<String, Integer> degreeOne = getDegreeOfSeparation(friendsLists, personOne);
		HashMap<String, Integer> degreeTwo = getDegreeOfSeparation(friendsLists, personTwo);
		int numDegreeOverSixOne = getNumDegreeOverSix(friendsLists, degreeOne);
		int numDegreeOverSixTwo = getNumDegreeOverSix(friendsLists, degreeTwo);

		if (numDegreeOverSixOne == numDegreeOverSixTwo)
			return "";
		return numDegreeOverSixOne < numDegreeOverSixTwo ? personOne : personTwo;
	}

	private static HashMap<String, Integer> getDegreeOfSeparation(Map<String, String[]> friendsLists,
			String origin) {
		HashMap<String, Integer> degree=new HashMap<String, Integer>();
		HashSet<String> visited=new HashSet<String>();
		ArrayDeque<PersonInfo> queue=new ArrayDeque<>();
		queue.add(new PersonInfo(origin, 0));
		while(queue.size()>0) {
			PersonInfo info=queue.peekFirst();
			degree.put(info.person, info.degree);
			for(String friend:friendsLists.get(info.person)) {
				if(visited.contains(friend)) continue;
				visited.add(friend);
				queue.add(new PersonInfo(friend, info.degree+1));
			}
		}
		for(String person:friendsLists.keySet()) {
			if(!visited.contains(person)) degree.put(person, Integer.MAX_VALUE);
		}
		return degree;
	}

	private static int getNumDegreeOverSix(Map<String, String[]> friendsLists, HashMap<String, Integer> degree) {
		int numDegreeOverSix = 0;
		for (String person : friendsLists.keySet()) {
			int distance = degree.get(person);
			if (distance > 6) {
				numDegreeOverSix++;
			}
		}
		return numDegreeOverSix;
	}

	static class PersonInfo {
		String person;
		int degree;

		public PersonInfo(String person, int degree) {
			this.person = person;
			this.degree = degree;
		}
	}

	public static void main(String[] args) {
		HashMap<String, String[]> friendsLists = new HashMap<String, String[]>();
		friendsLists.put("Aaron", new String[] { "Paul" });
		friendsLists.put("Akshay", new String[] {});
		friendsLists.put("Alex", new String[] { "Antoine", "Clement", "Ryan", "Simon" });
		friendsLists.put("Antoine", new String[] { "Alex", "Clement", "Simon" });
		friendsLists.put("Ayushi", new String[] { "Lee" });
		friendsLists.put("Changpeng", new String[] { "Kelly", "Sandeep" });
		friendsLists.put("Clement", new String[] { "Alex", "Antoine", "Sandeep", "Simon" });
		friendsLists.put("Hannah", new String[] { "Lexi", "Paul", "Sandeep" });
		friendsLists.put("James", new String[] { "Paul" });
		friendsLists.put("Kelly", new String[] { "Changpeng", "Molly" });
		friendsLists.put("Lee", new String[] { "Ayushi", "Molly" });
		friendsLists.put("Lexi", new String[] { "Hannah" });
		friendsLists.put("Molly", new String[] { "Kelly", "Lee" });
		friendsLists.put("Paul", new String[] { "Aaron", "James", "Hannah" });
		friendsLists.put("Ryan", new String[] { "Alex" });
		friendsLists.put("Sandeep", new String[] { "Changpeng", "Clement", "Hannah" });
		friendsLists.put("Simon", new String[] { "Alex", "Antoine", "Clement" });

		String personOne = "Antoine";
		String personTwo = "Clement";
		assertTrue(DegreesOfSeparation.degreesOfSeparation(friendsLists, personOne, personTwo).equals("Clement"));

	}

}
