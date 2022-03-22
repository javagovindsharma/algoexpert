package com.govind.alogexpert.easy;

import java.util.HashMap;
import java.util.Map;

public class NthFibonaci {
	public static int getNthFib1(int n) {
		if (n == 2)
			return 1;
		else if (n == 1)
			return 0;
		else
			return getNthFib(n - 1) + getNthFib(n - 2);

	}

	public static int getNthFib2(int n) {
		int[] last = { 0, 1 };
		int counter = 3;
		while (counter <= n) {
			int next = last[0] + last[1];
			last[0] = last[1];
			last[1] = next;
			counter++;
		}
		return n < 1 ? last[0] : last[1];

	}

	public static int getNthFib(int n) {
		if(n==0)
			return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                  map.put(1, 0); map.put(2, 1);
                  return getNthFib(n,map);
                  
	}
	public static int getNthFib(int n,Map<Integer, Integer> map) {
		if(map.containsKey(n)) {
			return map.get(n);
		}else {
		  map.put(n, getNthFib(n-1,map)+getNthFib(n-2,map));
		  return map.get(n);
		}	
                  
	}

	public static void main(String[] args) {
		int n = 0;
		System.out.println(getNthFib(n));

	}

}
