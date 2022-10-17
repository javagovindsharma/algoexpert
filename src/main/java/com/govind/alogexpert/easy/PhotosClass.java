package com.govind.alogexpert.easy;

import java.util.ArrayList;
import java.util.Collections;

public class PhotosClass {
	public static boolean classPhotos(ArrayList<Integer> redShirtHeights, ArrayList<Integer> blueShirtHeights) {
		String LargestShirtHeightColor = redShirtHeights.get(0) > blueShirtHeights.get(0) ? "blue" : "red";
		Collections.sort(redShirtHeights, Collections.reverseOrder());
		Collections.sort(blueShirtHeights, Collections.reverseOrder());
		for (int i = 0; i < redShirtHeights.size(); i++) {
			int redShirtHeight = redShirtHeights.get(i);
			int blueShirtHeight = blueShirtHeights.get(i);
			if (LargestShirtHeightColor == "red") {
				if (redShirtHeight >= blueShirtHeight)
					return false;
			} else {
				if (blueShirtHeight >= redShirtHeight)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		ArrayList<Integer> redShirtHeights = new ArrayList<Integer>();
		redShirtHeights.add(6);
		redShirtHeights.add(9);
		redShirtHeights.add(2);
		redShirtHeights.add(4);
		redShirtHeights.add(5);

		ArrayList<Integer> blueShirtHeights = new ArrayList<Integer>();
		blueShirtHeights.add(5);
		blueShirtHeights.add(8);
		blueShirtHeights.add(1);
		blueShirtHeights.add(3);
		blueShirtHeights.add(4);
		System.out.println(classPhotos(redShirtHeights,blueShirtHeights));
	}
}
