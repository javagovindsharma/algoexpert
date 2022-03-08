package com.govind.alogexpert.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TournamentMatch {
	final static int HOME_TEAM_WIN = 1;

	public  String tournamentWinnerSolve2(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
		Map<String,Integer> scores=new HashMap<String,Integer>();
		String currentBestTeam="";
	    scores.put(currentBestTeam, 0);
		for(int idx=0;idx<competitions.size();idx++) {
			ArrayList<String> competition=competitions.get(idx);
			String homeTeam=competition.get(0);
			String awayTeam=competition.get(1);
			int res=results.get(idx);
			String winTeam=HOME_TEAM_WIN==res?homeTeam:awayTeam;
			updateScores(winTeam,3,scores);
			if(scores.get(winTeam)>scores.get(currentBestTeam)) {
				currentBestTeam=winTeam;
			}
		}
		return currentBestTeam;
	}
	public void updateScores(String team,int points,Map<String,Integer> scores) {
		if(!scores.containsKey(team)) {
			scores.put(team, 0);
		}
		scores.put(team, scores.get(team)+3);
	}

	public static String tournamentWinnerSolve1(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < competitions.size(); i++) {
			String key = competitions.get(i).get(0);
			String keyWin = competitions.get(i).get(1);
			if (!map.containsKey(key)) {
				map.put(key, 0);
			}
			if (!map.containsKey(keyWin)) {
				map.put(keyWin, 0);
			}
			if (0 == results.get(i))
				map.put(keyWin, map.get(keyWin) + 3);
			else
				map.put(key, map.get(key) + 3);

		}
		return Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
	}

	public static void main(String[] args) {

		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("HTML");
		list1.add("C#");
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("C#");
		list2.add("Python");
		ArrayList<String> list3 = new ArrayList<String>();
		list3.add("Python");
		list3.add("HTML");

		ArrayList<ArrayList<String>> competitions = new ArrayList<ArrayList<String>>();
		competitions.add(list1);
		competitions.add(list2);
		competitions.add(list3);
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(0);
		result.add(0);
		result.add(1);
		TournamentMatch obj=new TournamentMatch();
		System.out.println(obj.tournamentWinnerSolve2(competitions, result));
	}

}
