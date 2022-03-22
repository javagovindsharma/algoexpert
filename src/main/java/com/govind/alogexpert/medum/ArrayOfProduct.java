package com.govind.alogexpert.medum;

import java.util.Arrays;

public class ArrayOfProduct {
	public static int[] arrayOfProducts2(int[] array) {
		int[] product = new int[array.length];
		int[] leftProducts = new int[array.length];
		int[] rightProducts = new int[array.length];
		int leftProduct=1,rightProduct=1;
		for (int i = 0; i < array.length; i++) {
			leftProducts[i]=leftProduct;
			leftProduct*=array[i];
		}
		for (int i =array.length-1;i>=0; i--) {
			rightProducts[i]=rightProduct;
			rightProduct*=array[i];
		}
		for (int i = 0; i < array.length; i++) {
			
			product[i]=leftProducts[i]*rightProducts[i];
		}

		return product;
	}
	
	public static int[] arrayOfProducts(int[] array) {
		int[] product = new int[array.length];
		
		int leftProduct=1,rightProduct=1;
		for (int i = 0; i < array.length; i++) {
			product[i]=leftProduct;
			leftProduct*=array[i];
		}
		for (int i =array.length-1;i>=0; i--) {
			product[i]*=rightProduct;
			rightProduct*=array[i];
		}
		return product;
	}

	public static int[] arrayOfProducts1(int[] array) {
		int[] product = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			int runningProduct = 1;
			for (int j = 0; j < array.length; j++) {
				if (j != i) {
					runningProduct *= array[j];
				}
			}
			product[i] = runningProduct;
		}

		return product;
	}

	public static void main(String[] args) {
		int[] array=new int[] {5, 1, 4, 2};
		
		System.out.println(Arrays.toString(arrayOfProducts(array)));
	}

}
