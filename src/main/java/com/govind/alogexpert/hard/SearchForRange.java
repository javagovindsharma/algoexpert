package com.govind.alogexpert.hard;

public class SearchForRange {

	// solution 2
	public static int[] searchForRange(int[] array, int target) {
		int[] finalRange = { -1, -1 };
		alteredBinarySearch(array, target, 0, array.length - 1, finalRange, true);
		alteredBinarySearch(array, target, 0, array.length - 1, finalRange, false);
		return finalRange;
	}

	private static void alteredBinarySearch(int[] array, int target, int left, int right, int[] finalRange,
			boolean goLeft) {

		if (left > right) {
			return;
		}
		int mid = (left + right) / 2;
		if (array[mid] < target) {
			alteredBinarySearch(array, target, mid+1, right,finalRange, goLeft);
		} else if (array[mid] > target) {
			alteredBinarySearch(array, target, left, mid-1, finalRange, goLeft);
		} else {
			if (goLeft) {
				if (mid == 0 || array[mid - 1] != target) {
					finalRange[0] = mid;
				} else {
					right = mid - 1;alteredBinarySearch(array, target, left, mid-1, finalRange, goLeft);
				}
			} else {
				if (mid == array.length - 1 || array[mid + 1] != target) {
					finalRange[1] = mid;
				} else {
					alteredBinarySearch(array, target, mid+1, right,finalRange, goLeft);
				}
			}
		}
	}

	// solution 1
	public static int[] searchForRange1(int[] array, int target) {
		int[] finalRange = { -1, -1 };
		alteredBinarySearch(array, target, 0, array.length - 1, finalRange, true);
		alteredBinarySearch(array, target, 0, array.length - 1, finalRange, false);
		return finalRange;
	}

	private static void alteredBinarySearch1(int[] array, int target, int left, int right, int[] finalRange,
			boolean goLeft) {

		while (left <= right) {
			int mid = (left + right) / 2;

			if (array[mid] < target) {
				left = mid + 1;
			} else if (array[mid] > target) {
				right = mid - 1;
			} else {
				if (goLeft) {
					if (mid == 0 || array[mid - 1] != target) {
						finalRange[0] = mid;
						return;
					} else {
						right = mid - 1;
					}
				} else {
					if (mid == array.length - 1 || array[mid + 1] != target) {
						finalRange[1] = mid;
						return;
					} else {
						left = mid + 1;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
