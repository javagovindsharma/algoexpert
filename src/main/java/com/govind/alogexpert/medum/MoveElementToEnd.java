package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MoveElementToEnd {

	public List<Integer> moveElementToEnd1(List<Integer> list, int toMove) {
		int i = 0;
		int j = list.size() - 1;
		while (i < j) {
			System.out.println(list.toString());
			while (i < j && list.get(j) == toMove) {
				j--;
			}
			if (list.get(i) == toMove) {
				swap(i, j, list);
			}
			i++;
		}
		return list;
	}

	public void swap(int i, int j, List<Integer> list) {
		int temp = list.get(j);
		list.set(j, list.get(i));
		list.set(i, temp);
	}

	public static void main(String[] args) {
		List<Integer> array = new ArrayList<Integer>(Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2));
		int toMove = 2;
		System.out.println(new MoveElementToEnd().moveElementToEnd2(array, toMove));
	}

	public List<Integer> moveElementToEnd2(List<Integer> list, int toMove) {
		List<Integer> listsametoMove = new ArrayList<Integer>();
		List<Integer> listnotsame2 = new ArrayList<Integer>();

		for (Integer val : list) {
			if (val == toMove) {
				listsametoMove.add(val);
			} else {

				listnotsame2.add(val);
			}
		}
		list.clear();
		Collections.sort(listnotsame2);
		list.addAll(listnotsame2);
		list.addAll(listsametoMove);
		return list;
	}

}
