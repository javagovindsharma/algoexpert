package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.Collections;

public class LapopRental {

	// solution 2
	public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
		if (times.size() == 0) {
			return 0;
		}

		int usedLaptops = 0;
		ArrayList<Integer> startTimes = new ArrayList<Integer>();
		ArrayList<Integer> endTimes = new ArrayList<Integer>();

		for (ArrayList<Integer> internal : times) {
			startTimes.add(internal.get(0));
			endTimes.add(internal.get(1));
		}
		Collections.sort(startTimes);
		Collections.sort(endTimes);

		int startIterator = 0;
		int endIterator = 0;

		while (startIterator < times.size()) {
			if (startTimes.get(startIterator) >= endTimes.get(endIterator)) {
				usedLaptops -= 1;
				endIterator += 1;
			}
			usedLaptops += 1;
			startIterator += 1;
		}
		return usedLaptops;
	}

	// solution 1
	public int laptopRentals1(ArrayList<ArrayList<Integer>> times) {
		if (times.size() == 0) {
			return 0;
		}
		Collections.sort(times, (a, b) -> Integer.compare(a.get(0), b.get(0)));

		ArrayList<ArrayList<Integer>> timesWhenLaptopIsUsed = new ArrayList<ArrayList<Integer>>();
		timesWhenLaptopIsUsed.add(times.get(0));
		MinHeap heap = new MinHeap(timesWhenLaptopIsUsed);

		for (int idx = 1; idx < times.size(); idx++) {
			ArrayList<Integer> currentInterval = times.get(idx);
			if (heap.peek().get(1) <= currentInterval.get(0)) {
				heap.remove();
			}
			heap.insert(currentInterval);
		}
		return timesWhenLaptopIsUsed.size();
	}

	class MinHeap {
		ArrayList<ArrayList<Integer>> heap = new ArrayList<ArrayList<Integer>>();

		public MinHeap(ArrayList<ArrayList<Integer>> array) {
			heap = buildHeap(array);
		}

		private ArrayList<ArrayList<Integer>> buildHeap(ArrayList<ArrayList<Integer>> array) {
			int firstParentIdx = (array.size() - 2) / 2;
			for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
				siftDown(currentIdx, array.size() - 1, array);
			}
			return array;
		}

		private void siftDown(int currentIdx, int endIdx, ArrayList<ArrayList<Integer>> heap) {
			int newCurrentIdx = currentIdx;
			int childOneIdx = currentIdx * 2 + 1;
			while (childOneIdx <= endIdx) {
				int childTwoIdx = newCurrentIdx * 2 + 2 <= endIdx ? newCurrentIdx * 2 + 2 : -1;
				int idxToSwap;
				if (childTwoIdx != -1 && heap.get(childTwoIdx).get(1) < heap.get(childOneIdx).get(1)) {
					idxToSwap = childTwoIdx;
				} else {
					idxToSwap = childOneIdx;
				}

				if (heap.get(idxToSwap).get(1) < heap.get(currentIdx).get(1)) {
					swap(newCurrentIdx, idxToSwap, heap);
					newCurrentIdx = idxToSwap;
					childOneIdx = newCurrentIdx * 2 + 1;
				} else {
					return;
				}
			}
		}

		public void siftUp(int currentIdx, ArrayList<ArrayList<Integer>> heap) {
			int newCurrentIdx = currentIdx;
			int parentIdx = (currentIdx - 1) / 2;
			while (currentIdx > 0 && heap.get(newCurrentIdx).get(1) < heap.get(parentIdx).get(1)) {
				swap(newCurrentIdx, parentIdx, heap);
				newCurrentIdx = parentIdx;
				parentIdx = (newCurrentIdx - 1) / 2;
			}
		}

		public ArrayList<Integer> peek() {
			return heap.get(0);
		}

		public ArrayList<Integer> remove() {
			swap(0, heap.size() - 1, heap);
			ArrayList<Integer> valueToRemove = heap.get(heap.size() - 1);
			heap.remove(heap.size() - 1);
			siftDown(0, heap.size() - 1, heap);
			return valueToRemove;
		}

		public void insert(ArrayList<Integer> value) {
			heap.add(value);
			siftUp(heap.size() - 1, heap);
		}

		public void swap(int i, int j, ArrayList<ArrayList<Integer>> heap) {
			ArrayList<Integer> temp = heap.get(j);
			heap.set(j, heap.get(i));
			heap.set(i, temp);
		}

		public boolean isEmpty() {
			return heap.size() == 0;
		}
	}

	public static void main(String[] args) {

	}

}
