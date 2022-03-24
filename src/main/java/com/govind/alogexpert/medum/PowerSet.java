package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
	public static List<List<Integer>> powerset1(List<Integer> array) {
		List<List<Integer>> subsets = new ArrayList<List<Integer>>();
		subsets.add(new ArrayList<Integer>());
		for (Integer ele : array) {
			int length = subsets.size();
			for (int i = 0; i < length; i++) {
				List<Integer> currentsubset = new ArrayList<Integer>(subsets.get(i));
				currentsubset.add(ele);
				subsets.add(currentsubset);
			}
		}
		return subsets;
	}

	public static List<List<Integer>> powerset2(List<Integer> array) {
		return powerset(array, array.size() - 1);
	}

	public static List<List<Integer>> powerset(List<Integer> array, int idx) {
		if (idx < 0) {
			List<List<Integer>> emptysets = new ArrayList<List<Integer>>();
			emptysets.add(new ArrayList<Integer>());
			return emptysets;
		}
		int ele=array.get(idx);
		List<List<Integer>> subsets = powerset(array,idx-1);
		int length=subsets.size();
		for(int i=0;i<length;i++) {
			List<Integer> currentsubset = new ArrayList<Integer>(subsets.get(i));
			currentsubset.add(ele);
			subsets.add(currentsubset);
		}
		return subsets;
	}

	public static List<List<Integer>> powerset(List<Integer> array) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(new ArrayList<Integer>());
		for (int j = 0; j < array.size(); j++) {
			List<Integer> subList = new ArrayList<Integer>();
			System.out.println(array.get(j));
			subList.add(array.get(j));
			list.add(subList);
		}
		for (int i = 0; i < array.size(); i++) {
			for (int j = i + 1; j < array.size(); j++) {
				List<Integer> subList = new ArrayList<Integer>();
				subList.add(array.get(i));
				subList.add(array.get(j));
				list.add(subList);
			}
		}
		list.add(array);
		return list;
	}

	public static void main(String[] args) {
		List<Integer> array = new ArrayList<Integer>();
		array.add(1);
		array.add(2);
		array.add(3);

		List<List<Integer>> list = powerset2(array);
		System.out.println(list.toString());
	}

}
