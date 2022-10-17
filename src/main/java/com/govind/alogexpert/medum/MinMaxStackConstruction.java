package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinMaxStackConstruction {

	static class MinMaxStack {
		List<Map<String, Integer>> minMaxStack = new ArrayList<Map<String, Integer>>();
		List<Integer> stack = new ArrayList<Integer>();

		public int peek() {
			return stack.get(stack.size() - 1);
		}

		public int pop() {
			minMaxStack.remove(minMaxStack.size() - 1);
			return stack.remove(stack.size() - 1);
		}

		public void push(Integer number) {
			Map<String, Integer> newMinMax = new HashMap<String, Integer>();
			newMinMax.put("min", number);
			newMinMax.put("max", number);
			if (minMaxStack.size() > 0) {
				@SuppressWarnings("unlikely-arg-type")
				Map<String, Integer> lastMinMax = new HashMap<String, Integer>(newMinMax.get(minMaxStack.size() - 1));
				newMinMax.replace("min", Math.min(lastMinMax.get("min"), number));
				newMinMax.replace("max", Math.max(lastMinMax.get("max"), number));
			}
			minMaxStack.add(newMinMax);
			stack.add(number);
		}

		public int getMin() {
			return minMaxStack.get(minMaxStack.size() - 1).get("min");
		}

		public int getMax() {
			return minMaxStack.get(minMaxStack.size() - 1).get("max");
		}
	}

	public static void testMinMaxPeek(int min, int max, int peek, MinMaxStackConstruction.MinMaxStack stack) {
		System.out.println(stack.getMin() == min);
		System.out.println(stack.getMax() == max);
		System.out.println(stack.peek() == peek);
	}

	public static void main(String[] args) {
		MinMaxStackConstruction.MinMaxStack stack = new MinMaxStackConstruction.MinMaxStack();
		stack.push(5);
		testMinMaxPeek(5, 5, 5, stack);
		stack.push(7);
		testMinMaxPeek(5, 7, 7, stack);
		stack.push(2);
		testMinMaxPeek(2, 7, 2, stack);
		System.out.println(stack.pop() == 2);
		System.out.println(stack.pop() == 7);
		testMinMaxPeek(5, 5, 5, stack);

	}

}
