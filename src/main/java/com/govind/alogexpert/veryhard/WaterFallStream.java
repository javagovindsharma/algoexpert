package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import org.jcp.xml.dsig.internal.dom.Utils;

public class WaterFallStream {

	public double[] waterfallStreams(double[][] array, int source) {
		double[] rowAbove = array[0];
		rowAbove[source] = -1;

		for (int row = 1; row < array.length; row++) {
			double[] currentRow = array[row];
			for (int idx = 0; idx < rowAbove.length; idx++) {
				double valueAbove = rowAbove[idx];
				boolean hasWaterAbove = valueAbove < 0;
				boolean hasBlock = currentRow[idx] == 1.0;

				if (!hasWaterAbove) {
					continue;
				}
				if (!hasBlock) {
					currentRow[idx] += valueAbove;
					continue;
				}
				double splitWater = valueAbove / 2;
				int rightIdx = idx;
				while (rightIdx + 1 < rowAbove.length) {
					rightIdx += 1;
					if (rowAbove[rightIdx] == 1.0) {
						break;
					}
					if (currentRow[rightIdx] != 1) {
						currentRow[rightIdx] += splitWater;
						break;
					}
				}
				// move water left
				int leftIdx = idx;
				while (leftIdx - 1 >= 0) {
					leftIdx -= 1;
					if (rowAbove[leftIdx] == 1.0) {
						break;
					}
					if (currentRow[leftIdx] != 1.0) {
						currentRow[leftIdx] += splitWater;
						break;
					}
				}
			}
			rowAbove = currentRow;
		}

		double[] finalPercentage = new double[rowAbove.length];
		for (int idx = 0; idx < rowAbove.length; idx++) {
			double num = rowAbove[idx];
			if (num == 0) {
				finalPercentage[idx] = num;
			} else {
				finalPercentage[idx] = (num * -100);
			}
		}

		return finalPercentage;
	}

	public static void main(String[] args) {
		double[][] array = new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, };
		int source = 3;
		double[] expected = { 0.0, 0.0, 0.0, 25.0, 25.0, 0.0, 0.0 };
		double[] actual = new WaterFallStream().waterfallStreams(array, source);
		assertTrue(expected.length == actual.length);
		for (int i = 0; i < expected.length; i++)
			assertTrue(expected[i] == actual[i]);

	}

}
