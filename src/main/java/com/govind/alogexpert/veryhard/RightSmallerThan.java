package com.govind.alogexpert.veryhard;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RightSmallerThan {

	// soluton1
	public static List<Integer> rightSmallerThan1(List<Integer> array) {
		List<Integer> rightSmallerCounts = new ArrayList<Integer>();
		for (int i = 0; i < array.size(); i++) {
			int rightSmallerCount = 0;
			for (int j = i + 1; j < array.size(); j++) {
				if (array.get(j) < array.get(i)) {
					rightSmallerCount++;
				}
			}
			rightSmallerCounts.add(rightSmallerCount);
		}
		return rightSmallerCounts;
	}

	// solution 2

	public static List<Integer> rightSmallerThan2(List<Integer> array) {
		if (array.size() == 0)
			return new ArrayList<Integer>();

		int lastIdx = array.size() - 1;
		SpecialBST2 bst = new SpecialBST2(array.get(lastIdx), lastIdx, 0);
		for (int i = array.size() - 2; i >= 0; i--) {
			bst.insert(array.get(i), i);
		}
		List<Integer> rightSmallerCounts = new ArrayList<Integer>(array);
		getRightSmallerCounts(bst, rightSmallerCounts);

		return rightSmallerCounts;
	}

	private static void getRightSmallerCounts(SpecialBST2 bst, List<Integer> rightSmallerCounts) {
		if (bst == null)
			return;
		rightSmallerCounts.set(bst.idx, bst.numSmallerAtInsertTime);
		getRightSmallerCounts(bst.left, rightSmallerCounts);
		getRightSmallerCounts(bst.right, rightSmallerCounts);
	}

	static class SpecialBST2 {
		int value;
		int idx;
		int numSmallerAtInsertTime;
		int leftSubtreeSize = 0;
		SpecialBST2 left;
		SpecialBST2 right;

		public SpecialBST2(int value, int idx, int numSmallerAtInsertTime) {
			this.value = value;
			this.idx = idx;
			this.numSmallerAtInsertTime = numSmallerAtInsertTime;
			left = null;
			right = null;
		}

		void insert(int value, int idx) {
			insertHelper(value, idx, 0);
		}

		private void insertHelper(int value, int idx, int numSmallerAtInsertTime) {
			if (value < this.value) {
				leftSubtreeSize++;
				if (left == null) {
					left = new SpecialBST2(value, idx, numSmallerAtInsertTime);
				} else {
					left.insertHelper(value, idx, numSmallerAtInsertTime);
				}
			} else {
				numSmallerAtInsertTime += leftSubtreeSize;
				if (value > this.value)
					numSmallerAtInsertTime++;
				if (right == null) {
					right = new SpecialBST2(value, idx, numSmallerAtInsertTime);
				} else {
					right.insertHelper(value, idx, numSmallerAtInsertTime);
				}
			}
		}
	}

	// solution 3
	public static List<Integer> rightSmallerThan(List<Integer> array) {
		if (array.size() == 0)
			return new ArrayList<Integer>();
		List<Integer> rightSmallerCounts = new ArrayList<Integer>(array);
		int lastIdx = array.size() - 1;
		SpecialBST bst = new SpecialBST(array.get(lastIdx));
		rightSmallerCounts.set(lastIdx, 0);
		for (int i = array.size() - 2; i >= 0; i--) {
			bst.insert(array.get(i), i, rightSmallerCounts);
		}
		return rightSmallerCounts;
	}

	static class SpecialBST {
		int value;
		int leftSubtreeSize = 0;
		SpecialBST left;
		SpecialBST right;

		public SpecialBST(int value) {
			this.value = value;
			leftSubtreeSize = 0;
			left = null;
			right = null;
		}

		void insert(int value, int idx, List<Integer> rightSmallerCounts) {
			insertHelper(value, idx, rightSmallerCounts, 0);
		}

		private void insertHelper(int value, int idx, List<Integer> rightSmallerCounts, int numSmallerAtInsertTime) {
			if (value < this.value) {
				leftSubtreeSize++;
				if (left == null) {
					left = new SpecialBST(value);
					rightSmallerCounts.set(idx, numSmallerAtInsertTime);
				} else {
					left.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
				}
			} else {
				numSmallerAtInsertTime += leftSubtreeSize;
				if (value > this.value)
					numSmallerAtInsertTime++;
				if (right == null) {
					right = new SpecialBST(value);
					rightSmallerCounts.set(idx, numSmallerAtInsertTime);
				} else {
					right.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
				}
			}
		}
	}

	public static void main(String[] args) {
		List<Integer> array = Arrays.asList(8, 5, 11, -1, 3, 4, 2);
		List<Integer> expected = Arrays.asList(5, 4, 4, 0, 1, 1, 0);
		List<Integer> actual = new RightSmallerThan().rightSmallerThan(array);
		assertTrue(expected.equals(actual));
	}

}
