package com.govind.alogexpert.medum;

public class SingleCycleCheck {

	public static boolean hasSingleCycle(int[] array) {
		int numElementsVisits = 0;
		int currentIdx = 0;
		while (numElementsVisits < array.length) {
			if (numElementsVisits > 0 && currentIdx == 0)
				return false;
			numElementsVisits++;
			currentIdx = getNextIdx(currentIdx, array);
		}
		return currentIdx == 0;
	}

	public static int getNextIdx(int currentIdx, int[] array) {
		int jump = array[currentIdx];
		int nextIdx = (currentIdx + jump) % array.length;
		return nextIdx >= 0 ? nextIdx : nextIdx + array.length;
	}

	public static void main(String[] args) {
		SingleCycleCheck.hasSingleCycle(new int[] { 2, 3, 1, -4, -4, 2 });

	}

}
