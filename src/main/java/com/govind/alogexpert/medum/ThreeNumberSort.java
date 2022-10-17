package com.govind.alogexpert.medum;

public class ThreeNumberSort {
	public int[] threeNumberSort1(int[] array, int[] order) {
		int[] arr = new int[array.length];
		int count = 0;
		for (int ordernum : order) {
			for (int i = 0; i < array.length; i++) {
				if (ordernum == array[i]) {
					arr[count] = array[i];
					count++;
				}
			}
		}
		return arr;
	}
	
	public int[] threeNumberSort2(int[] array, int[] order) {
		int firstValue=order[0];
		int secondValue=order[0];
		
		int firstIdx=0;
		int secondIdx=0;
		int thirdIdx=array.length-1;
		while(secondIdx<=thirdIdx) {
			int value=array[secondIdx];
			if(value==firstValue) {
				 swap(firstIdx,secondIdx,array);
				 firstIdx+=1;
				 secondIdx+=1;
			}else if(value==secondValue){
				secondIdx+=1;
			}else {
				swap(secondIdx,thirdIdx,array);
				thirdIdx-=1;
			}
		}
		return array;
	}

	public void swap(int i,int j,int[] array) {
		int temp=array[j];
	    array[j]=array[i];
	    array[i]=temp;
	}
	
	public int[] threeNumberSort3(int[] array, int[] order) {
		int[] valueCounts=new int[] {0,0,0};
		 for(int element:array) {
			 int orderIdx=getIndex(order,element);
			 valueCounts[orderIdx]+=1;
		 }
		 
		 for(int i=0;i<3;i++) {
			 int value=order[i];
			 int count= valueCounts[i];
			 
			 int numElementsBefore=getSum(valueCounts,i);
			  for(int n=0;n<count;n++) {
				  int currentIdx=numElementsBefore+n;
			    array[currentIdx]=value;
			  }
		 }
		 return array;
	}
	
	@SuppressWarnings("unused")
	public int[] threeNumberSort(int[] array, int[] order) {
		int firstValue=order[0];
		int thirdValue=order[2];
		
		int firstIdx=0;
		for(int idx=0;idx<array.length;idx++) {
			if(array[idx]==firstValue) {
				swap(firstIdx, idx, array);
				firstIdx+=1;
			}
		}
		int thirdIdx=array.length-1;
		for(int idx=array.length;idx>=0;idx--) {
			if(array[idx]==thirdValue) {
				swap(firstIdx, idx, array);
				thirdIdx-=1;
			}
		}
		return array;
	}
	
	
	public int getIndex(int[] array,int element) {
		 for(int i=0;i<array.length;i++) {
			 if(array[i]==element) {
				 return i;
			 }
		 }
		 return -1;
	}
	
	public int getSum(int[] array,int end) {
		 int sum=0;
		 for(int i=0;i<end;i++) sum+=array[i];
		 return sum;
	}
	
	public static void main(String[] args) {
		int[] array = new int[] {1, 0, 0, -1, -1, 0, 1, 1};
	    int[] order = new int[] {0, 1, -1};
	    int[] expected = new int[] {0, 0, 0, 1, 1, 1, -1, -1};
	    int[] actual = new ThreeNumberSort().threeNumberSort(array, order);
	    for (int i = 0; i < expected.length; i++) {
	      System.out.println(expected[i] == actual[i]);
	    }

	}

}
