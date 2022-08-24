package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;


public class StackSort {
	public ArrayList<Integer> sortStack(ArrayList<Integer> stack) {
		if (stack.size() == 0) {
			return stack;
		}
		int top=stack.remove(stack.size()-1);
		sortStack(stack);
		insertInSortedOrder(stack,top);
		return stack;
	}

	private void insertInSortedOrder(ArrayList<Integer> stack, int value) {
		if(stack.size()==0|| (stack.get(stack.size()-1)<=value)) {
			stack.add(value);
			return;
		}
		int top=stack.remove(stack.size()-1);
		insertInSortedOrder(stack, value);
		stack.add(top);
	}

	public static void main(String[] args) {
		ArrayList<Integer> stack = new ArrayList<Integer>(Arrays.asList(-5, 2, -2, 4, 3, 1));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(-5, -2, 1, 2, 3, 4));
		Object actual = new StackSort().sortStack(stack);
		System.err.println(expected.equals(actual));

	}

}
