package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
	public static List<List<Integer>> getPermutations1(List<Integer> array) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		getPermutations(array, new ArrayList<Integer>(), list);
		return list;
	}

	public static void getPermutations(List<Integer> array, List<Integer> current, List<List<Integer>> permutations) {
		if (array.size() == 0 && current.size() > 0) {
			permutations.add(current);
		} else {
			for (int i = 0; i < array.size(); i++) {
				List<Integer> newArray = new ArrayList<Integer>(array);
				newArray.remove(i);
				List<Integer> newPermutation = new ArrayList<Integer>(current);
				newPermutation.add(array.get(i));
				getPermutations(newArray, newPermutation, permutations);
			}
		}
	}

	public static List<List<Integer>> getPermutations(List<Integer> array) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		getPermutations(0,array, list);
		return list;
	}

	public static void getPermutations(int i, List<Integer> array, List<List<Integer>> permutations) {
		if (i == array.size() - 1) {
			permutations.add(new ArrayList<Integer>(array));
		} else {
			for (int j = i; j < array.size(); j++) {
                 swap(array,i,j);
                 getPermutations(i+1,array,permutations);
                 swap(array,i,j);
			}
		}
	}

	

	private static void swap(List<Integer> array, int i, int j) {
		Integer temp=array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
		
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		List<List<Integer>> perms = getPermutations(input);

		System.out.println(perms);
	}
}
