package com.govind.alogexpert.easy;

import java.util.ArrayList;
import java.util.List;

public class ProductSum {

	public static int productSum(List<Object> array) {
		return productSumHelper(array, 1);
	}

	public static int productSumHelper(List<Object> array, int multiplier) {
		int sum = 0;
		for (Object obj : array) {
			if (obj instanceof ArrayList) {
				@SuppressWarnings("unchecked")
				ArrayList<Object> ob = (ArrayList<Object>) obj;
				sum += productSumHelper(ob, multiplier + 1);
			} else {
				sum += (int) obj;
			}
		}
		return sum * multiplier;
	}

	public static void main(String[] args) {
		//Object[] array=new Object[] {5, 2, [7, -1], 3, {6, {-13, 8}, 4}};
	    List<Object> list= new ArrayList<Object>();
	                 list.add(5);list.add(2);
	              ArrayList<Object> l1=new ArrayList<Object>();
	                                l1.add(7);l1.add(-1);
	                 list.add(l1);               
	                 list.add(3);
	              ArrayList<Object> l2=new ArrayList<Object>();
	                                l2.add(6);
	                                ArrayList<Object> l3=new ArrayList<Object>();
	                                                  l3.add(-13);l3.add(8);
	                                l2.add(l3);
	                                l2.add(4);
	                 list.add(l2);               
	                 
		System.out.println(productSum(list));
	}

}
