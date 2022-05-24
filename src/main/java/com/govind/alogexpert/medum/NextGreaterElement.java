package com.govind.alogexpert.medum;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

public class NextGreaterElement {

	public int[] nextGreaterElementSolution1(int[] array) {
		int[] resultArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			int result = array[i];
			boolean flag = false;
			for (int j = i; j < array.length; j++) {
				if (array[j] > result) {
					result = array[j];
					break;
				}

				if (flag && j == array.length - 1) {
					result = -1;
					break;
				}
				if (j == array.length - 1) {
					j = -1;
					flag = true;
				}

			}
			resultArray[i] = result;
		}

		return resultArray;
	}

	public int[] nextGreaterElementSolution2(int[] array) {
		int[] result = new int[array.length];
		Arrays.fill(result, -1);
		Stack<Integer> stack = new Stack<Integer>();
		for (int idx = 0; idx < 2 * array.length; idx++) {
			int circularIdx = idx % array.length;
			while (stack.size() > 0 && array[stack.peek()] < array[circularIdx]) {
				int top = stack.pop();
				result[top] = array[circularIdx];
			}
			stack.push(circularIdx);
		}
		return result;
	}

	public int[] nextGreaterElement(int[] array) {
		int[] result = new int[array.length];
		Arrays.fill(result, -1);
		Stack<Integer> stack = new Stack<Integer>();
		for (int idx = 2 * array.length - 1; idx >= 0; idx--) {
			int circularIdx = idx % array.length;
			while (stack.size() > 0) {
				if (stack.peek() <= array[circularIdx]) {
					stack.pop();
				} else {
					result[circularIdx] = stack.peek();
					break;
				}
			}
			stack.push(array[circularIdx]);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("hi");
		NextGreaterElement obj = new NextGreaterElement();
		int[] input = new int[] { 6, 4, 5, 7, 2, 1, 3 };// { 2, 5, -3, -4, 6, 7, 2 };
		int[] expected = new int[] { 1, 2, 3, 4, -1 };// { 5, 6, 6, 6, 7, -1, 5 };
		int[] actual = obj.nextGreaterElement(input);
		System.out.println(Arrays.toString(actual));
		// obj.testFindMax(expected,actual);
	}

	@Test
	public void testFindMax(int[] expected, int[] actual) {
		assertEquals(expected, actual);
	}

}
