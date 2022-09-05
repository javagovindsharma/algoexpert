package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MinReward {
	// solution 3
	public static int minRewards(int[] scores) {
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);
		for (int i = 1; i < scores.length; i++) {
			if (scores[i] > scores[i - 1])
				rewards[i] = rewards[i - 1] + 1;

		}
		for (int i =scores.length-2; i>=0  ;i--) {
			if (scores[i] > scores[i + 1])
				rewards[i] = Math.max(rewards[i],rewards[i + 1] + 1);

		}

		return IntStream.of(rewards).sum();
	}

	// solution 2
	public static int minRewards2(int[] scores) {
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);
		List<Integer> localMinIdxs = getLocalMinIdxs(scores);
		for (Integer localMinIdx : localMinIdxs) {
			expandFromLocalMinIdx(localMinIdx, scores, rewards);
		}
		return IntStream.of(rewards).sum();
	}

	private static List<Integer> getLocalMinIdxs(int[] scores) {
		List<Integer> localMinIdxs = new ArrayList<Integer>();
		if (scores.length == 1) {
			return localMinIdxs;
		}
		for (int i = 0; i < scores.length; i++) {
			if (i == 0 && scores[i] < scores[i + 1])
				localMinIdxs.add(i);
			if (i == scores.length - 1 && scores[i] < scores[i - 1])
				localMinIdxs.add(i);
			if (i == 0 || i == scores.length - 1)
				continue;
			if (scores[i] < scores[i + 1] && scores[i] < scores[i - 1])
				localMinIdxs.add(i);
		}
		return localMinIdxs;
	}

	private static void expandFromLocalMinIdx(Integer localMinIdx, int[] scores, int[] rewards) {
		int leftIdx = localMinIdx - 1;
		while (leftIdx >= 0 && scores[leftIdx] > scores[leftIdx + 1]) {
			rewards[leftIdx] = Math.max(rewards[leftIdx], rewards[leftIdx + 1] + 1);
			leftIdx--;
		}
		int rightIdx = localMinIdx + 1;
		while (rightIdx < scores.length && scores[rightIdx] > scores[rightIdx - 1]) {
			rewards[rightIdx] = rewards[rightIdx - 1] + 1;
			rightIdx++;
		}

	}

//solution 1
	public static int minRewards1(int[] scores) {
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);
		for (int i = 0; i < scores.length; i++) {
			int j = i - 1;
			if (scores[i] > scores[j]) {
				rewards[i] = rewards[j] + 1;
			} else {
				while (j >= 0 && scores[j] > scores[j + 1]) {
					rewards[j] = Math.max(rewards[j], rewards[j + 1] + 1);
					j--;
				}
			}
		}
		return IntStream.of(rewards).sum();
	}

	public static void main(String[] args) {
		minRewards(new int[] { 8, 4, 2, 1, 3, 6, 7, 9, 5 });

	}

}
