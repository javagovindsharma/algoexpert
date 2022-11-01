package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.govind.alogexpert.veryhard.A_Algorithm.MinHeap;

public class MergeSortedArrays {

	// solution 1
	public static List<Integer> mergeSortedArrays1(List<List<Integer>> arrays) {
		List<Integer> sortedList = new ArrayList<Integer>();
		List<Integer> elementIdxs = new ArrayList<Integer>(Collections.nCopies(arrays.size(), 0));

		while (true) {
			List<Item> smallestItems = new ArrayList<Item>();
			for (int arrayIdx = 0; arrayIdx < arrays.size(); arrayIdx++) {
				List<Integer> relevantArray = arrays.get(arrayIdx);
				int elementIdx = elementIdxs.get(arrayIdx);
				if (elementIdx == relevantArray.size())
					continue;
				smallestItems.add(new Item(arrayIdx, relevantArray.get(elementIdx) + 1));
			}

			if (smallestItems.size() == 0)
				break;
			Item nextItem = getMinValue(smallestItems);
			sortedList.add(nextItem.num);
			elementIdxs.set(nextItem.arrayIdx, elementIdxs.get(nextItem.arrayIdx));
		}

		return sortedList;
	}

	private static Item getMinValue(List<Item> items) {
		int minValueIdx = 0;
		for (int i = 1; i < items.size(); i++) {
			if (items.get(i).num < items.get(minValueIdx).num)
				minValueIdx = i;
		}
		return items.get(minValueIdx);
	}

	static class Item {
		int arrayIdx;
		int num;

		public Item(int arrayIdx, int num) {
			super();
			this.arrayIdx = arrayIdx;
			this.num = num;
		}

	}

	// solution 2
	public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
		List<Integer> sortedList = new ArrayList<Integer>();
		List<Items> smallestItems = new ArrayList<Items>();

		for (int arrayIdx = 0; arrayIdx < arrays.size(); arrayIdx++) {
			smallestItems.add(new Items(arrayIdx, 0, arrays.get(arrayIdx).get(0)));
		}

		MinHeap minHeap = new MinHeap(smallestItems);

		while (!minHeap.isEmpty()) {
              Items smallestItem=minHeap.remove();
              sortedList.add(smallestItem.num);
              if(smallestItem.elementidx==arrays.get(smallestItem.arrayIdx).size()-1) continue;
              minHeap.insert(
            		  new Items( smallestItem.arrayIdx,
            				  smallestItem.elementidx+1,
            				  arrays.get(smallestItem.arrayIdx).get(smallestItem.elementidx+1))
            		  );
		}

		return sortedList;
	}

	static class Items {
		int arrayIdx;
		int elementidx;
		int num;

		Items(int arrayIdx, int elementIdx, int num) {
			this.arrayIdx = arrayIdx;
			this.elementidx = elementIdx;
			this.num = num;
		}
	}

	static class MinHeap {
		List<Items> heap = new ArrayList<Items>();

		public MinHeap(List<Items> array) {
			heap = buildHeap(array);
		}

		private List<Items> buildHeap(List<Items> array) {
			int firstParentIdx = (array.size() - 2) / 2;
			for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
				siftDown(currentIdx, array.size() - 1, array);
			}
			return array;
		}

		private void siftDown(int currentIdx, int endIdx, List<Items> heap) {
			int childOneIdx = currentIdx * 2 + 1;
			while (childOneIdx <= endIdx) {
				int childtwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;
				int idxToSwap;
				if (childtwoIdx != -1 && heap.get(childtwoIdx).num < heap.get(childOneIdx).num) {
					idxToSwap = childtwoIdx;
				} else {
					idxToSwap = childOneIdx;
				}

				if (heap.get(idxToSwap).num < heap.get(currentIdx).num) {
					swap(currentIdx, idxToSwap, heap);
					currentIdx = idxToSwap;
					childOneIdx=currentIdx*2+1;
				}else {
					return;
				}
			}
		}

		public void siftUp(int currentIdx,List<Items> heap) {
			int parentIdx=(currentIdx-1)/2;
			while(currentIdx>0 && heap.get(currentIdx).num<heap.get(parentIdx).num) {
				swap(currentIdx, parentIdx, heap);
				currentIdx = parentIdx;
				parentIdx=(currentIdx-1)/2;
			}
		}
		public Items remove() {
			swap(0,heap.size()-1,heap);
			Items valueToRemove=heap.get(heap.size()-1);
			heap.remove(heap.size()-1);
			siftDown(0, heap.size()-1, heap);
			return valueToRemove;
		}
		
		private void swap(int i, int j, List<Items> heap) {
		  Items temp=heap.get(j);
		  heap.set(j, heap.get(i));
		  heap.set(i, temp);
		}

		public void insert(Items value) {
			heap.add(value);
			siftUp(heap.size()-1, heap);
		}
		public boolean isEmpty() {
			return heap.size() == 0;
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> arrays = new ArrayList<List<Integer>>();
		arrays.add(Arrays.asList(new Integer[] { 1, 5, 9, 21 }));
		arrays.add(Arrays.asList(new Integer[] { -1, 0 }));
		arrays.add(Arrays.asList(new Integer[] { -124, 81, 121 }));
		arrays.add(Arrays.asList(new Integer[] { 3, 6, 12, 20, 150 }));
		List<Integer> actual = MergeSortedArrays.mergeSortedArrays(arrays);
		List<Integer> expected = Arrays.asList(new Integer[] { -124, -1, 0, 1, 3, 5, 6, 9, 12, 20, 21, 81, 121, 150 });
		assertTrue(actual.equals(expected));
	}

}
